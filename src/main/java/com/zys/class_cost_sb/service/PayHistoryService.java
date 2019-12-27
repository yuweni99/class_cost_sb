package com.zys.class_cost_sb.service;

import com.zys.class_cost_sb.pojo.PayHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/26
 */
public interface PayHistoryService extends IService<PayHistory> {

    /**
     * 创建订单并发起支付
     * @param chargingHistoryId 班费缴费id
     * @return
     */
    String createOrder(Integer chargingHistoryId);

    /**
     * 修改支付状态
     * @param params
     * @return
     */
    boolean updatePayStatus(Map<String, String> params);
}
