package com.zys.class_cost_sb.model.response;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public enum ClassesCode implements ResultCode {
    CLASSES_EXISTS_STU(false,20001,"操作失败，当前班级已经存在学生");


    private boolean success;

    private int code;

    private String message;

    private ClassesCode(boolean success, int code, String message) {
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
