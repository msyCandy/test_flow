package com.msytools.testflow.backend.api.task;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msytools.testflow.backend.api.task.form.*;
import com.msytools.testflow.backend.api.task.vo.GetTaskVo;
import com.msytools.testflow.backend.common.annotations.NeedLogin;
import com.msytools.testflow.backend.common.enums.DealTypeEnum;
import com.msytools.testflow.backend.common.exception.ClientException;
import com.msytools.testflow.backend.entity.TaskEntity;
import com.msytools.testflow.backend.entity.dto.PageVo;
import com.msytools.testflow.backend.entity.dto.RespVo;
import com.msytools.testflow.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@NeedLogin
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 新建测试任务
     */
    @PostMapping("/create_task")
    public RespVo<Integer> createTask(@RequestBody @Validated CreateTaskForm form) {
        Integer taskId = taskService.createTask(form);
        return RespVo.success(taskId);
    }

    /**
     * 关闭测试任务
     */
    @PostMapping("/close_task")
    public RespVo closeTask(@RequestBody @Validated CloseTaskForm form) {
        taskService.closeTask(form);
        return RespVo.success();
    }

    /**
     * 获取测试任务列表
     */
    @GetMapping("/get_task")
    public RespVo<PageVo<GetTaskVo>> getTask(GetTaskForm form) {
        Page<TaskEntity> page = taskService.getTask(form);
        List<GetTaskVo> voList = page.getRecords().stream().map(GetTaskVo::mapping).collect(Collectors.toList());
        return RespVo.success(new PageVo<>(form.getPageNum(), form.getPageSize(), voList, page.getTotal()));
    }

    @PostMapping("/report_bug")
    public RespVo reportBug(@RequestBody @Validated ReportBugForm form) {
        taskService.reportBug(form);
        return RespVo.success();
    }

    @PostMapping("/deal_bug")
    public RespVo dealBug(@RequestBody @Validated DealBugForm form) {
        if (!DealTypeEnum.checkType(form.getDealType())) {
            throw new ClientException("dealType参数错误");
        }
        taskService.dealBug(form);
        return RespVo.success();
    }
}
