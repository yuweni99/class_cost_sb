package com.zys.class_cost_sb.service;

import com.zys.class_cost_sb.pojo.Charging;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ChargingService extends IService<Charging> {


    /**
     * 根据班费管理员查询缴费公告
     * @param userId 班费管理员id
     * @return
     */
    List<Map<String, Object>> findAllByUserId(Integer userId);

    /**
     * 保存缴费记录
     * @param charging
     */
    void saveCharging(Charging charging);
}
