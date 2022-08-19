package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msytools.testflow.backend.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationDao extends BaseMapper<OrganizationEntity> {
}
