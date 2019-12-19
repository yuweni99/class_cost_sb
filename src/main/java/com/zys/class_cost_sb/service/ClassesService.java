package com.zys.class_cost_sb.service;

import com.zys.class_cost_sb.pojo.Classes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ClassesService extends IService<Classes> {


    /**
     * 查询所有班级名称并查询所属负责人
     *
     * @return
     */
    List<Map<String, Object>> findAll();

    /**
     * 删除班级
     * @param id
     */
    void del(Integer id);

    /**
     * 根据班费管理员查询所管理的班级
     * @param userId
     * @return
     */
    List<Map<String, Object>> findAllByUserId(Integer userId);

    /**
     * 根据班费管理员查询班级是否存在
     * @param userId 班费管理员id
     * @param classId 班级id
     * @return
     */
    Integer countByUserIdAndClassId(Integer userId, Integer classId);
}
