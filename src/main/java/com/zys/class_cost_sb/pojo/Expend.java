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
@TableName(value = "expend")
public class Expend {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "money")
    private Double money;

    @TableField(value = "use_details")
    private String useDetails;

    @TableField("class_id")
    private Integer classId;

    /**
     * 班级管理员关联，表示是谁添加的记录
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "create_time")
    private Date createTime;

}