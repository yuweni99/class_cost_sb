package com.zys.class_cost_sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zys.class_cost_sb.pojo.Charging;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ChargingMapper extends BaseMapper<Charging> {
    List<Map<String, Object>> findAllByUserId(Integer userId);

    @Override
    int insert(Charging charging);
}