package com.msytools.testflow.backend.api.user.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePasswordForm {
    @NotBlank(message = "密码不得为空")
    private String password;
}
