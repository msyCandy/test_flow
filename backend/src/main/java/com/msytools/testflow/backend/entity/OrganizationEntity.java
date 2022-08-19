package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("organization")
public class OrganizationEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private Integer createUserId;

    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
}
