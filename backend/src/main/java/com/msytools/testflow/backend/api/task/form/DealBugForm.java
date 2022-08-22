package com.msytools.testflow.backend.api.task.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DealBugForm {
    @NotNull(message = "请求参数错误")
    private Integer bugId;

    private Integer nextUserId;
    @NotNull(message = "请求参数错误")
    private Integer dealType;
    @NotNull(message = "请求参数错误")
    private String content;
    @NotNull(message = "请求参数错误")
    private List<String> files;
}
