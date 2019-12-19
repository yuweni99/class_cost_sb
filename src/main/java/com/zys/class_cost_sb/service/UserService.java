package com.zys.class_cost_sb.service;

import com.zys.class_cost_sb.model.request.Pwd;
import com.zys.class_cost_sb.model.request.UserExt;
import com.zys.class_cost_sb.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface UserService extends IService<User> {


    /**
     * 登陆
     *
     * @param userInfoExt
     * @return
     */
    User login(UserExt userExt);

    /**
     * 注册
     * @param userExt
     * @return
     */
    void register(UserExt userExt);

    /**
     * 根据角色类型查询用户
     * @param roleType
     * @return
     */
    List<Map<String,Object>> findAllByRoleType(Integer roleType);

    /**
     * 删除班级管理员
     * @param id
     */
    void delClassConstManager(Integer id);

    /**
     * 修改密码
     * @param pwd 密码数据
     * @param id 用户id
     */
    void updatePwd(Pwd pwd, Integer id);

    /**
     * 根据状态查询本班人员
     * @param classId 班级id
     * @param status 学生状态
     * @return
     */
    List<Map<String, Object>> findStuByStatusAndClassId(Integer classId, Integer status,Integer stuRoleType);

    /**
     * 激活用户
     * @param id
     */
    void active(Integer id);

    /**
     * 重置密码
     * @param id
     */
    void resetPwd(Integer id);

    /**
     * 获取同班同学
     * @param id 学生id
     * @return
     */
    List<Map<String, Object>> findClassFellow(Integer id);
}
