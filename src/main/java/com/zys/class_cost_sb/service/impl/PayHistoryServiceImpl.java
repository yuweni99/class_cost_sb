package com.zys.class_cost_sb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.config.AlipayConfig;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.mapper.ChargingHistoryMapper;
import com.zys.class_cost_sb.mapper.ChargingMapper;
import com.zys.class_cost_sb.mapper.PayHistoryMapper;
import com.zys.class_cost_sb.pojo.Charging;
import com.zys.class_cost_sb.pojo.ChargingHistory;
import com.zys.class_cost_sb.pojo.PayHistory;
import com.zys.class_cost_sb.service.PayHistoryService;
import com.zys.class_cost_sb.utils.DateUtils;
import com.zys.class_cost_sb.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/26
 */
@Slf4j
@Service
public class PayHistoryServiceImpl extends ServiceImpl<PayHistoryMapper, PayHistory> implements PayHistoryService {

    @Autowired
    private ChargingHistoryMapper chargingHistoryMapper;

    @Autowired
    private ChargingMapper chargingMapper;

    @Autowired
    private AlipayConfig alipayConfig;


    @Override
    @Transactional
    public String createOrder(Integer chargingHistoryId) {

        // 查询班费缴费信息
        ChargingHistory chargingHistory = chargingHistoryMapper.selectById(chargingHistoryId);

        // 获取订单需支付金额
        Charging charging = chargingMapper.selectById(chargingHistory.getChargingId());

        // 创建支付历史记录
        String uuid = UuidUtils.getUuid();

        savePayHistory(uuid, chargingHistory, charging);

        // 发起支付
        String result = null;
        try {
            result = initiationOfPayments(uuid, charging);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @Transactional
    public boolean updatePayStatus(Map<String, String> params) {

        //商户订单号
        String outTradeNo = params.get("out_trade_no");

        //支付宝交易号
        String tradeNo = params.get("trade_no");


        // 根据商户订单号查询支付订单
        PayHistory payHistory = baseMapper.selectById(outTradeNo);

        // 查询不到支付订单
        if(null == payHistory){
            log.error("查询不到订单号为{}的支付订单",outTradeNo);
            return false;
        }

        // 校验支付金额
        String buyerPayAmount = params.get("buyer_pay_amount"); // 用户付款金额

        if(!payHistory.getMoney().equals(Double.valueOf(buyerPayAmount))){
            log.error("用户支付金额{}少于需支付金额{}",buyerPayAmount,payHistory.getMoney());
            return false;
        }

        // 修改支付状态

        Date gmtPayment = DateUtils.parse(params.get("gmt_payment"));

        payHistory.setTradeNo(tradeNo); // 保存支付宝交易号
        payHistory.setStatus(SysConstant.END_PAYMENT); // 修改支付状态

        payHistory.setPayTime(gmtPayment);

        baseMapper.updateById(payHistory);

        // 修改班费缴费历史记录状态
        ChargingHistory chargingHistory = chargingHistoryMapper.selectById(payHistory.getChargingHistoryId());
        chargingHistory.setStatus(SysConstant.PAID_FEE_STATUS);
        chargingHistory.setUpdateTime(gmtPayment);

        chargingHistoryMapper.updateById(chargingHistory);

        log.info("支付成功支付金额为{}，支付订单为{}",buyerPayAmount,outTradeNo);
        return true;
    }

    /**
     * 发起支付
     *
     * @param uuid
     * @param charging
     * return 请求表单
     */
    private String initiationOfPayments(String uuid, Charging charging) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(), alipayConfig.getMerchantPrivateKey(),
                "json", alipayConfig.getCharset(), alipayConfig.getAliPayPublicKey(), alipayConfig.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

        Map<String,Object> payParamMap = new HashMap<>();

        payParamMap.put("out_trade_no",uuid);
        payParamMap.put("total_amount",charging.getMoney());
        payParamMap.put("subject","班费缴纳");
        payParamMap.put("body",charging.getChargingDesc());
        payParamMap.put("product_code","FAST_INSTANT_TRADE_PAY");
        payParamMap.put("timeout_express",alipayConfig.getTimeoutExpress());

        // 转json
        String payParamJson = JSON.toJSONString(payParamMap);

        alipayRequest.setBizContent(payParamJson);

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        log.info("支付请求表单:{}",result);

        return result;
    }

    /**
     * 保存支付订单
     *
     * @param uuid
     * @param chargingHistory
     */
    private void savePayHistory(String uuid, ChargingHistory chargingHistory, Charging charging) {


        PayHistory payHistory = new PayHistory(
                uuid,
                new Date(),
                chargingHistory.getUserId(),
                charging.getMoney(),
                SysConstant.ALI_PAY_STATUS,
                chargingHistory.getId(),
                SysConstant.NO_PAYMENT_STATUS
        );

        baseMapper.insert(payHistory);
    }
}
