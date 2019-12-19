package com.zys.class_cost_sb.model.request;

import com.zys.class_cost_sb.pojo.User;
import lombok.Data;

/**
 * @Author hong
 * @Date 2019/12/9
 */
@Data
public class UserExt extends User {
    /**
     * 验证码
     */
    private String identifyingCode;
}
