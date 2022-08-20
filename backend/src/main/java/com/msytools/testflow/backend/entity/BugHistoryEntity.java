package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bug_history")
public class BugHistoryEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer bugId;
    private Integer userId;
    private Integer newResponsibleUserId;
    private Integer type;
    private String content;
    private String files;

    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
}
