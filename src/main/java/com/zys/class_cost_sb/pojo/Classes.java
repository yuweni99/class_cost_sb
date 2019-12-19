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
@TableName(value = "classes")
public class Classes {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 班级名称
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 班费管理员id
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "create_time")
    private Date createTime;

}