package com.zys.class_cost_sb.controoler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zys.class_cost_sb.config.AlipayConfig;
import com.zys.class_cost_sb.service.PayHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/26
 * 支付接口
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private PayHistoryService payHistoryService;


    /**
     * 支付宝支付
     * @return
     */
    @GetMapping("/aliPayPayment")
    public String aliPayPayment(@RequestParam("id") Integer chargingHistoryId, Model model){

        // 创建订单并发起支付
        String result = payHistoryService.createOrder(chargingHistoryId);

        model.addAttribute("result",result);


        return "manager/pay/index";
    }

    /**
     * 支付同步回调
     * @return
     */
    @GetMapping("/aliPaySyncBack")
    public String returnUrl() {
        return "redirect:/chargingHistory/findStuChargingHistory?isPay=1";
    }

    /**
     * 异支付宝步回调
     * @return
     */
    @PostMapping("/aliPayAsyncBack")
    @ResponseBody
    public String notifyUrl() throws AlipayApiException {

        Map<String,String> params = getParams();


        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //交易状态
            String trade_status = request.getParameter("trade_status");

            // 交易成功
            if(trade_status.equals("TRADE_SUCCESS")){

            }

        }else {//验证失败
            //调试用，写文本函数记录程序运行情况是否正常
            String sWord = AlipaySignature.getSignCheckContentV1(params);
            AlipayConfig.logResult(sWord);
            return "fail";
        }

        return "success";
    }

    /**
     * 获取请求参数
     * @return
     */
    private Map<String, String> getParams() {

        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        return params;
    }


}
