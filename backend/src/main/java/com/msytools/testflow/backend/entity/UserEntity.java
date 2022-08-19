package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;
    private String password;
    private String name;

    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
}
