package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msytools.testflow.backend.entity.BugEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BugDao extends BaseMapper<BugEntity> {
}
