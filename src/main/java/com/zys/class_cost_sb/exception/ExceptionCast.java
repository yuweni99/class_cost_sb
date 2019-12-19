package com.zys.class_cost_sb.exception;


import com.zys.class_cost_sb.model.response.ResultCode;

/**
 * @author lib
 * @qq 446630668
 * @date 2019/3/5
 * 抛出自定义异常快捷类
 */
public class ExceptionCast {
    /**
     * 抛出自定义异常
     * @param resultCode
     */
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }

}
