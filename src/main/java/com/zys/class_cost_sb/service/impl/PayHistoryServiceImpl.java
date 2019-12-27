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

    /**
     * 发起支付
     *
     * @param uuid
     * @param charging
     * return 请求表单
     */
    private String initiationOfPayments(String uuid, Charging charging) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        Map<String,Object> payParamMap = new HashMap<>();

        payParamMap.put("out_trade_no",uuid);
        payParamMap.put("total_amount",charging.getMoney());
        payParamMap.put("subject","班费缴纳");
        payParamMap.put("body",charging.getChargingDesc());
        payParamMap.put("product_code","FAST_INSTANT_TRADE_PAY");
        payParamMap.put("timeout_express",AlipayConfig.timeout_express);

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
