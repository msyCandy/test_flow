package com.msytools.testflow.backend.api.task.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msytools.testflow.backend.entity.TaskEntity;
import lombok.Data;

import java.util.Date;

@Data
public class GetTaskVo {
    private Integer taskId;
    private Integer createUserId;
    private String taskName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public static GetTaskVo mapping(TaskEntity entity) {
        GetTaskVo vo = new GetTaskVo();
        vo.setTaskId(entity.getId());
        vo.setCreateUserId(entity.getCreateUserId());
        vo.setTaskName(entity.getName());
        vo.setCreateTime(entity.getCreateTime());

        return vo;
    }
}
