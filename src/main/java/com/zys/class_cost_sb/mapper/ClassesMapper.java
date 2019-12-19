package com.zys.class_cost_sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zys.class_cost_sb.pojo.Classes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ClassesMapper extends BaseMapper<Classes> {
    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findAllByUserId(Integer userId);

    Integer countByUserIdAndClassId(@Param("userId") Integer userId, @Param("classId") Integer classId);
}