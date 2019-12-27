package com.zys.class_cost_sb.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hong
 * @Date 2019/12/9
 * web工具类
 */
public class WebUtils {

    /**
     * 查看是否为ajax请求
     * @return true 为ajax请求 false则不是
     */
    public static boolean isAjax(HttpServletRequest request){

        String header = request.getHeader("X-Requested-With");

        return !StringUtils.isEmpty(header);
    }

}
