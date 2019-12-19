package com.zys.class_cost_sb.model.response;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public interface ResultCode {

    /**
     * 是否成功
     * @return
     */
    boolean success();

    /**
     * 状态码
     * @return
     */
    int code();

    /**
     * 错误信息
     * @return
     */
    String message();

}
