package com.msytools.testflow.backend.api.task.form;

import lombok.Data;

@Data
public class CreateTaskForm {
    private Integer orgId;
    private String taskName;
}
