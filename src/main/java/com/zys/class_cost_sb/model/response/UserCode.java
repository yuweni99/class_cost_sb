package com.zys.class_cost_sb.model.response;

import com.zys.class_cost_sb.constant.SysConstant;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public enum UserCode implements ResultCode {
    USER_NOT_EXISTS(false,10001,"用户不存在"),
    USER_EXISTS(false,10005,"当前用户系统已存在"),
    User_PASSWORD_ERROR(false,10002,"用户名或密码错误"),
    USER_STATUS_ERROR(false,10004,"用户账户未激活，请联系班级管理员"),
    IDENTIFYING_CODE_NOT_IDENTICAL(false,10003,"验证码不正确,请重新输入"),
    CLASS_CODE_MANAGER_HAS_BEEN_MANAGED_CLASS(false,10006,"操作失败，当前管理员已管理班级"),
    OLD_PWD_ERROR(false,10008,"操作失败，原密码不正确"),
    OLD_PWD_NWE_PWD_NOT_CONSISTENT(false,10009,"操作失败，新密码不能与旧密码相同"),
    RESET_PWD_SUCCESS(true,10010,"重置密码成功，默认密码为" + SysConstant.DEFAULT__PASSWORD);

    private boolean success;

    private int code;

    private String message;

    private UserCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean success() {
        return this.success;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
