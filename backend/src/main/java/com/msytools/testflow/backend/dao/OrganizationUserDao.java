package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msytools.testflow.backend.entity.OrganizationUserEntity;
import com.msytools.testflow.backend.entity.dto.OrganizationUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganizationUserDao extends BaseMapper<OrganizationUserEntity> {

    /**
     * 获取用户所在的组织
     * @param userId
     * @return
     */
    List<OrganizationUserDTO> getUserOrg(@Param("userId") int userId);
}
