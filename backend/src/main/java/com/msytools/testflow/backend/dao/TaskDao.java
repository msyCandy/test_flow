package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msytools.testflow.backend.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskDao extends BaseMapper<TaskEntity> {

    Page<TaskEntity> pageTask(Page page,
                              @Param("orgId") int orgId,
                              @Param("state") Integer state);
}
