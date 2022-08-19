package com.msytools.testflow.backend.api.organization.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddUserForm {
    @NotBlank(message = "账户不得为空")
    @Length(min = 6, max = 16, message = "账户长度错误")
    private String account;

    @NotNull(message = "请求参数错误")
    private Integer roleId;

    @NotNull(message = "请求参数错误")
    private Integer orgId;
}
