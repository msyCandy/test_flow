package com.msytools.testflow.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msytools.testflow.backend.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 修改用户密码
     * @param userId
     * @param password
     * @return
     */
    int updatePassword(@Param("userId") int userId,
                       @Param("password") String password);
}
