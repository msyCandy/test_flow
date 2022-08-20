package com.msytools.testflow.backend.api.organization.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetUserListForm {
    @NotNull(message = "请求参数错误")
    private Integer orgId;

    private Integer roleId;
}
