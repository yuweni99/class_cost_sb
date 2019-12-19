package com.zys.class_cost_sb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author hong
 * @Date 2019/12/17
 */
@Data
@TableName(value = "charging_history")
public class ChargingHistory {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField("charging_id")
    private Integer chargingId;

    /**
     * 缴费状态 0 未缴费，1缴费
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 缴费时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}