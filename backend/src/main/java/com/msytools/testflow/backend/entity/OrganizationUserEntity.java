package com.msytools.testflow.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("organization_user")
public class OrganizationUserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer organizationId;
    private Integer userId;
    private Integer roleId;

    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
}
