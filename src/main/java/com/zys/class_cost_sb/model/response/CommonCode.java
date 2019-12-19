package com.zys.class_cost_sb.model.response;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public enum  CommonCode implements ResultCode {
    SUCCESS(true,50001,"操作成功"),
    SYSTEM_ERROR(false,50002,"系统出错了~"),
    ILLEGAL_OPERATION (false,50003,"非法操作！！！");

    private boolean success;

    private int code;

    private String message;

    private CommonCode(boolean success, int code, String message) {
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
