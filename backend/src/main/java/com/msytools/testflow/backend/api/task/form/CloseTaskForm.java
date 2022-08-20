package com.msytools.testflow.backend.api.task.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CloseTaskForm {
    @NotNull(message = "请求参数错误")
    private Integer taskId;
}
