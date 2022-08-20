package com.msytools.testflow.backend.api.task.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReportBugForm {
    @NotNull(message = "请求参数错误")
    private Integer taskId;

    @NotBlank(message = "标题不得为空")
    @Length(max = 32, message = "标题过长")
    private String title;

    @NotBlank(message = "描述不得为空")
    @Length(max = 1024, message = "描述过长")
    private String content;

    @NotNull(message = "请求参数错误")
    private List<String> files;
}
