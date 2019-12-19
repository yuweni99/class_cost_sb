package com.zys.class_cost_sb.utils;

import com.zys.class_cost_sb.constant.SysConstant;

import javax.servlet.http.HttpSession;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public class IdentifyingCodeUtils {

    /**
     * 验证码对比工具类
     * @param session
     * @param IdentifyingCode 验证码
     * @return
     */
    public static boolean contrastIdentifyingCode(HttpSession session, String identifyingCode){

        Object sessionIdentifyingCode = session.getAttribute(SysConstant.IDENTIFYING_CODE_SESSION_KEY);

        if(null == sessionIdentifyingCode){
            return false;
        }

        return ((String)sessionIdentifyingCode).equalsIgnoreCase(identifyingCode);
    }

}
