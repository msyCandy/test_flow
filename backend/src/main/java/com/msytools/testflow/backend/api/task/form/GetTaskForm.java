package com.msytools.testflow.backend.api.task.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GetTaskForm {
    @NotNull(message = "请求参数错误")
    private Integer orgId;

    private Integer state;

    @NotNull(message = "页码不得为空")
    @Min(value = 1, message = "页码错误")
    private Integer pageNum;

    @NotNull(message = "页长不得为空")
    @Range(min = 10, max = 50, message = "页长数据不合法")
    private Integer pageSize;
}
