package com.zys.class_cost_sb.utils;

import java.util.UUID;

/**
 * @Author hong
 * @Date 2019/12/26
 */
public class UuidUtils {

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
