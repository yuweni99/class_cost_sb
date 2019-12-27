package com.zys.class_cost_sb.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hong
 * @Date 2019/12/26
 */
@Data
@NoArgsConstructor
public class PayReturn {

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 支付宝交易号
     */
    private String trade_no;

    /**
     * 交易状态
     */
    private String trade_status;
}

