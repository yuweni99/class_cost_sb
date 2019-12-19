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
@TableName(value = "user")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "username")
    private String username;

    /**
     * 班级id
     */
    @TableField(value = "class_id")
    private Integer classId;

    @TableField(value = "password")
    private String password;

    /**
     *  1：系统管理员 2：班费管理员,3: 学生
     */
    @TableField(value = "role_type")
    private Integer roleType;

    /**
     * 激活状态 0未激活，1激活
     */
    private Integer status;


    @TableField(value = "create_time")
    private Date createTime;

}