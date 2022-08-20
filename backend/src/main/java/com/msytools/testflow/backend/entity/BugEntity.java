package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("bug")
public class BugEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer taskId;
    private String title;
    private Integer createUserId;
    private Integer responsibleUserId;
    private Integer state;

    @TableField(fill = FieldFill.DEFAULT)
    private Integer finish;
}
