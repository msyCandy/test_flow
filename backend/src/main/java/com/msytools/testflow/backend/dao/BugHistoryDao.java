package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msytools.testflow.backend.entity.BugHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BugHistoryDao extends BaseMapper<BugHistoryEntity> {
}
