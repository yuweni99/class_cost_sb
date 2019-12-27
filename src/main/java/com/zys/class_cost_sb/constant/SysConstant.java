package com.zys.class_cost_sb.constant;

/**
 * @Author hong
 * @Date 2019/12/9
 * 系统常量类
 */
public class SysConstant {

    /**
     * 密码加盐
     */
    public static final String PASSWORD_SALT = "zys";

    /**
     * 验证码session存放key名
     */
    public static final String IDENTIFYING_CODE_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    /**
     * 学生角色
     */
    public static final Integer STUDENT_ROLE = 3;

    /**
     * 班费管理员角色
     */
    public static final Integer CLASS_COST_MANAGER_ROLE = 2;

    /**
     * 系统管理员角色
     */
    public static final Integer SYS_MANAGER_ROLE = 1;


    /**
     * 用户激活状态
     */
    public static final Integer USER_IS_ACTIVE = 1;

    /**
     * 用户未激活状态
     */
    public static final Integer USER_NOT_ACTIVE = 0;

    /**
     * 系统默认密码
     */
    public static final String DEFAULT__PASSWORD = "123456";

    /**
     * 未缴费
     */
    public static final Integer UNPAID_FEE_STATUS = 0;

    /**
     * 已缴费
     */
    public static final Integer PAID_FEE_STATUS = 1;

    /**
     * 未支付状态
     */
    public static final Integer NO_PAYMENT_STATUS = 0;


    /**
     * 已支付
     */
    public static final Integer END_PAYMENT = 1;

    /**
     * 支付宝支付
     */
    public static final Integer ALI_PAY_STATUS = 1;

    /**
     * 支付成功状态
     */
    public static final String PAY_SUCCESS_STATUS = "TRADE_SUCCESS";

}
