package com.zys.class_cost_sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zys.class_cost_sb.pojo.Expend;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ExpendMapper extends BaseMapper<Expend> {
    List<Map<String, Object>> findAllByUserId(Integer userId);

    List<Map<String, Object>> findStuExpend(Integer id);
}