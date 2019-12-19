package com.zys.class_cost_sb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.exception.ExceptionCast;
import com.zys.class_cost_sb.mapper.ClassesMapper;
import com.zys.class_cost_sb.mapper.UserMapper;
import com.zys.class_cost_sb.model.request.Pwd;
import com.zys.class_cost_sb.model.request.UserExt;
import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.UserCode;
import com.zys.class_cost_sb.pojo.Classes;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.UserService;
import com.zys.class_cost_sb.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public User login(UserExt userExt) {

        // 根据用户名查询用户
        User user = ((UserMapper)baseMapper).findByUserName(userExt.getUsername());

        if(null == user){
            ExceptionCast.cast(UserCode.USER_NOT_EXISTS);
        }

        // 校验密码
        String password = Md5Util.getMd5(userExt.getPassword() + SysConstant.PASSWORD_SALT);

        if(!user.getPassword().equals(password)){
            ExceptionCast.cast(UserCode.User_PASSWORD_ERROR);
        }

        // 查询当前角色是否为学生,并且查看是否激活账户
        if(SysConstant.STUDENT_ROLE.equals(user.getRoleType()) &&
                SysConstant.USER_NOT_ACTIVE.equals(user.getStatus())){
            ExceptionCast.cast(UserCode.USER_STATUS_ERROR);
        }


        return user;
    }

    @Override
    @Transactional
    public void register(UserExt userExt) {

        User user = ((UserMapper)baseMapper).findByUserName(userExt.getUsername());

        if(null != user){
            ExceptionCast.cast(UserCode.USER_EXISTS);
        }

        String password = userExt.getPassword();

        // 密码加密
        String encryptionPassword = Md5Util.getMd5(password + SysConstant.PASSWORD_SALT);

        userExt.setPassword(encryptionPassword);
        userExt.setRoleType(SysConstant.STUDENT_ROLE);
        userExt.setCreateTime(new Date());
        userExt.setStatus(SysConstant.USER_NOT_ACTIVE);

        // 插入数据
        baseMapper.insert(userExt);

    }

    @Override
    public List<Map<String, Object>> findAllByRoleType(Integer roleType) {
        return  ((UserMapper)baseMapper).findAllByRoleType(roleType);
    }

    @Override
    public void delClassConstManager(Integer id) {

        User user = baseMapper.selectById(id);

        // 非法操作
        if(!SysConstant.CLASS_COST_MANAGER_ROLE.equals(user.getRoleType())){
            ExceptionCast.cast(CommonCode.ILLEGAL_OPERATION);
        }

        // 查询当前班费管理员是否管理班级
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        Integer count = classesMapper.selectCount(queryWrapper);

        // 已管理班级
        if(count > 0){
            ExceptionCast.cast(UserCode.CLASS_CODE_MANAGER_HAS_BEEN_MANAGED_CLASS);
        }

        // 删除管理员
        baseMapper.deleteById(id);
    }

    @Override
    public void updatePwd(Pwd pwd, Integer id) {

        // 校验原密码是否正确
        String oldPwd = Md5Util.getMd5(pwd.getOldPwd() + SysConstant.PASSWORD_SALT);

        User user = baseMapper.selectById(id);

        if(!oldPwd.equals(user.getPassword())){
            ExceptionCast.cast(UserCode.OLD_PWD_ERROR);
        }

        // 修改密码
        String newPwd = Md5Util.getMd5(pwd.getNewPwd() + SysConstant.PASSWORD_SALT);

        // 原密码新密码不能一样
        if(oldPwd.equals(newPwd)){
            ExceptionCast.cast(UserCode.OLD_PWD_NWE_PWD_NOT_CONSISTENT);
        }

        user.setPassword(newPwd);

        baseMapper.updateById(user);
    }

    @Override
    public List<Map<String, Object>> findStuByStatusAndClassId(Integer classId, Integer status,Integer stuRoleType) {
        return ((UserMapper)baseMapper).findSutByStatus(classId,status,stuRoleType);
    }

    @Override
    public void active(Integer id) {
        ((UserMapper)baseMapper).updateStatus(id,SysConstant.USER_IS_ACTIVE);
    }

    @Override
    public void resetPwd(Integer id) {
        User user = baseMapper.selectById(id);
        String defaultPassword = Md5Util.getMd5(SysConstant.DEFAULT__PASSWORD + SysConstant.PASSWORD_SALT);
        user.setPassword(defaultPassword);

        // 更新数据
        baseMapper.updateById(user);
    }

    @Override
    public List<Map<String, Object>> findClassFellow(Integer id) {
        return ((UserMapper)baseMapper).findClassFellow(id);
    }

}
