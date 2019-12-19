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
@TableName(value = "charging")
public class Charging {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 金额
     */
    @TableField(value = "money")
    private Double money;

    /**
     * 收费说明
     */
    @TableField(value = "charging_desc")
    private String chargingDesc;

    /**
     * 班级管理员关联
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "create_time")
    private Date createTime;

}