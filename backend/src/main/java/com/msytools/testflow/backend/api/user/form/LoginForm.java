package com.msytools.testflow.backend.api.user.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank(message = "账户不得为空")
    @Length(min = 6, max = 16, message = "账户长度错误")
    private String account;

    @NotBlank(message = "密码不得为空")
    private String password;
}
