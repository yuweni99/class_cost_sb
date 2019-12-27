package com.zys.class_cost_sb.controoler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zys.class_cost_sb.config.AlipayConfig;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.service.PayHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AlipayConfig alipayConfig;


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
    public String aliPaySyncBack() {
        return "manager/pay/status";
    }

    /**
     * 异支付宝步回调
     * @return
     */
    @PostMapping(value = "/aliPayAsyncBack",produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String aliPayAsyncBack(@RequestParam Map<String,String> params) throws AlipayApiException {
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAliPayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名

        if(signVerified) {//验证成功

            //交易状态
            String tradeStatus = request.getParameter("trade_status");

            // 校验交易是否成功
            if(!SysConstant.PAY_SUCCESS_STATUS.equals(tradeStatus)){
                logger.error("交易失败");
                return "fail";
            }

            // 校验app_id是否为商户本身
            String appId = params.get("app_id");

            if(!alipayConfig.getAppId().equals(appId)){
                logger.error("当前支付app_id不是商户本身");
                return "fail";
            }

            // 修改订单支付状态
            boolean success = payHistoryService.updatePayStatus(params);

            if(!success){
                return "fail";
            }

            return "success";
        }else {//验证失败
            //调试用，写文本函数记录程序运行情况是否正常
            String sWord = AlipaySignature.getSignCheckContentV1(params);
            alipayConfig.logResult(sWord);
            return "fail";
        }

    }


}
