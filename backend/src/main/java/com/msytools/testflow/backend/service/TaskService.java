package com.msytools.testflow.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msytools.testflow.backend.api.task.form.CloseTaskForm;
import com.msytools.testflow.backend.api.task.form.CreateTaskForm;
import com.msytools.testflow.backend.api.task.form.GetTaskForm;
import com.msytools.testflow.backend.api.task.form.ReportBugForm;
import com.msytools.testflow.backend.common.enums.RoleEnum;
import com.msytools.testflow.backend.common.exception.ClientException;
import com.msytools.testflow.backend.dao.TaskDao;
import com.msytools.testflow.backend.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TaskService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private TaskDao taskDao;

    /**
     * 新建测试任务
     *
     * @param form
     * @return
     */
    public Integer createTask(CreateTaskForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (!organizationService.checkRole(form.getOrgId(), userId, RoleEnum.TEST)) {
            throw new ClientException("仅测试人员可创建测试任务");
        }
        TaskEntity task = taskDao.selectOne(Wrappers.<TaskEntity>lambdaQuery()
                .eq(TaskEntity::getOrganizationId, form.getOrgId())
                .eq(TaskEntity::getName, form.getTaskName()));
        if (task != null) {
            throw new ClientException("已存在此测试任务");
        }
        task = new TaskEntity();
        task.setName(form.getTaskName());
        task.setOrganizationId(form.getOrgId());
        task.setCreateUserId(userId);
        try {
            taskDao.insert(task);
        } catch (RuntimeException e) {
            throw new ClientException("已存在此测试任务");
        }
        return task.getId();
    }

    /**
     * 关闭测试任务
     *
     * @param form
     */
    public void closeTask(CloseTaskForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        TaskEntity taskEntity = taskDao.selectById(form.getTaskId());
        if (taskEntity == null) {
            throw new ClientException("无此测试任务");
        }
        if (!taskEntity.getCreateUserId().equals(userId)) {
            throw new ClientException("此测试任务不是您创建的，无法关闭");
        }
        taskEntity.setState(0);
        taskDao.updateById(taskEntity);
    }

    /**
     * 获取测试任务
     *
     * @param form
     * @return
     */
    public Page<TaskEntity> getTask(GetTaskForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer roleId = organizationService.getUserRole(form.getOrgId(), userId);
        if (roleId == null) {
            throw new ClientException("您不是此组织内的成员");
        }
        return taskDao.pageTask(new Page<>(form.getPageNum(), form.getPageSize()), form.getOrgId(), form.getState());
    }

    /**
     * 报告bug
     *
     * @param form
     */
    public void reportBug(ReportBugForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        TaskEntity task = taskDao.selectById(form.getTaskId());
        if (task == null) {
            throw new ClientException("测试任务不存在");
        }
        if (task.getState().equals(0)) {
            throw new ClientException("测试任务已关闭");
        }
        Integer roleId = organizationService.getUserRole(task.getOrganizationId(), userId);
        if (!roleId.equals(RoleEnum.TEST.getRoleId())) {
            throw new ClientException("您无权报告BUG");
        }

    }
}
