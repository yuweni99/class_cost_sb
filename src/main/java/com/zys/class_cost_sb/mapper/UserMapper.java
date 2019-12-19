package com.zys.class_cost_sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zys.class_cost_sb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUserName(String username);

    List<Map<String, Object>> findAllByRoleType(Integer roleType);

    /**
     * 根据班级id统计当前班级下学生人数
     *
     * @param id
     * @param classId
     * @return
     */
    Integer countStuNumberByClassId(@Param("roleType") Integer roleType, @Param("classId") Integer classId);

    List<User> selectByClassId(@Param("classId") Integer classId, @Param("roleType") Integer roleType);

    List<Map<String, Object>> findSutByStatus(@Param("classId") Integer classId, @Param("status") Integer status, @Param("stuRoleType") Integer stuRoleType);

    /**
     * 激活
     *
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    List<Map<String, Object>> findClassFellow(Integer id);
}