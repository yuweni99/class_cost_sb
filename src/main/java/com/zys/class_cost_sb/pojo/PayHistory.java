package com.zys.class_cost_sb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author hong
 * @Date 2019/12/26
 */
@Data
@NoArgsConstructor
@TableName(value = "pay_history")
public class PayHistory {


    public PayHistory(String id, Date createTime, Integer userId, Double money, Integer payType, Integer chargingHistoryId, Integer status) {
        this.id = id;
        this.createTime = createTime;
        this.userId = userId;
        this.money = money;
        this.payType = payType;
        this.chargingHistoryId = chargingHistoryId;
        this.status = status;
    }



    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 订单创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 支付宝交易号
     */
    @TableField(value = "trade_no")
    private String tradeNo;

    /**
     * 订单修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 订单发起人
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 支付金额
     */
    @TableField(value = "money")
    private Double money;

    /**
     * 支付方式1：支付宝 2:微信
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 班级缴费历史记录id
     */
    @TableField(value = "charging_history_id")
    private Integer chargingHistoryId;

    /**
     * 状态，1已支付，0未支付
     */
    @TableField(value = "status")
    private Integer status;
}