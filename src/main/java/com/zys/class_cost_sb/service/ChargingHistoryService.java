package com.zys.class_cost_sb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zys.class_cost_sb.model.request.ChargingHistoryRequest;
import com.zys.class_cost_sb.pojo.ChargingHistory;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ChargingHistoryService extends IService<ChargingHistory> {


    /**
     * 根据缴费状态查询缴费情况
     * @param status
     * @return
     */
    List<Map<String, Object>> findAllByStatus(ChargingHistoryRequest chargingHistoryRequest);

    /**
     * 根据学生id获取缴费记录
     * @param userId 学生id
     * @return
     */
    List<Map<String, Object>> findStuChargingHistory(Integer userId);

    /**
     * 缴费
     * @param id
     * @param userId 用户id
     */
    void payment(Integer id, Integer userId);
}
