package com.zys.class_cost_sb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.mapper.ChargingHistoryMapper;
import com.zys.class_cost_sb.model.request.ChargingHistoryRequest;
import com.zys.class_cost_sb.pojo.ChargingHistory;
import com.zys.class_cost_sb.service.ChargingHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @Author hong
 * @Date 2019/12/17
 */
@Service
public class ChargingHistoryServiceImpl extends ServiceImpl<ChargingHistoryMapper, ChargingHistory> implements ChargingHistoryService{

    @Override
    public List<Map<String, Object>> findAllByStatus(ChargingHistoryRequest chargingHistoryRequest) {
        return  ((ChargingHistoryMapper) baseMapper).findAllByStatus(chargingHistoryRequest);
    }

    @Override
    public List<Map<String, Object>> findStuChargingHistory(Integer userId) {
        return  ((ChargingHistoryMapper) baseMapper).findStuChargingHistory(userId);
    }

    @Override
    public void payment(Integer id, Integer userId) {
        ((ChargingHistoryMapper) baseMapper).updateStatusByIdAndUserId(id,userId, SysConstant.PAID_FEE_STATUS);
    }
}
