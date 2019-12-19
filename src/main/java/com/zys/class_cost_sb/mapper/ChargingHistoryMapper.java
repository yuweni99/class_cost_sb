package com.zys.class_cost_sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zys.class_cost_sb.model.request.ChargingHistoryRequest;
import com.zys.class_cost_sb.pojo.ChargingHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ChargingHistoryMapper extends BaseMapper<ChargingHistory> {
    List<Map<String, Object>> findAllByStatus(ChargingHistoryRequest chargingHistoryRequest);

    List<Map<String, Object>> findStuChargingHistory(Integer userId);

    void updateStatusByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId, @Param("status") Integer status);
}