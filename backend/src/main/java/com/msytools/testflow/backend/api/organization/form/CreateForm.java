package com.msytools.testflow.backend.api.organization.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CreateForm {
    @NotBlank(message = "组织名称不得为空")
    @Length(min = 2, max = 16, message = "组织名称长度错误")
    private String name;
}
