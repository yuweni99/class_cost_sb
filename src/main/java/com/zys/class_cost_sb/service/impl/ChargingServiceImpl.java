package com.zys.class_cost_sb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.mapper.ChargingHistoryMapper;
import com.zys.class_cost_sb.mapper.ChargingMapper;
import com.zys.class_cost_sb.mapper.UserMapper;
import com.zys.class_cost_sb.pojo.Charging;
import com.zys.class_cost_sb.pojo.ChargingHistory;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ChargingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @Author hong
 * @Date 2019/12/17
 */
@Service
public class ChargingServiceImpl extends ServiceImpl<ChargingMapper, Charging> implements ChargingService{


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChargingHistoryMapper chargingHistoryMapper;

    @Override
    public List<Map<String, Object>> findAllByUserId(Integer userId) {
        return ((ChargingMapper) baseMapper).findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void saveCharging(Charging charging) {

        if(null == charging.getId()){
            charging.setCreateTime(new Date());

            ((ChargingMapper) baseMapper).insert(charging);

            batchAddChargingHistory(charging.getId(),charging.getClassId());

        }else{
            // 清理班级id，防止前端非法操作
            charging.setClassId(null);
            baseMapper.updateById(charging);
        }



    }

    /**
     * 批量插入收缴历史记录
     * @param chargingId
     * @param classId
     */
    private void batchAddChargingHistory(Integer chargingId,Integer classId) {

        // 查询当前班级下所有学生
        List<User> users = userMapper.selectByClassId(classId, SysConstant.STUDENT_ROLE);

        List<ChargingHistory> chargingHistories = new ArrayList<>();
        // 创建历史记录
        for (User user : users) {

            ChargingHistory chargingHistory = new ChargingHistory();

            chargingHistory.setStatus(SysConstant.UNPAID_FEE_STATUS);
            chargingHistory.setUserId(user.getId());
            chargingHistory.setChargingId(chargingId);
            chargingHistory.setCreateTime(new Date());

            chargingHistories.add(chargingHistory);
        }


        batchAdd(chargingHistories);

    }

    /**
     * 批量插入
     * @param chargingHistories
     */
    private void batchAdd(List<ChargingHistory> chargingHistories) {

        chargingHistories.forEach((c) -> {
            chargingHistoryMapper.insert(c);
        });


    }
}
