package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("task")
public class TaskEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer organizationId;
    private String name;
    private Integer createUserId;

    @TableField(fill = FieldFill.DEFAULT)
    private Integer state;

    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
}
