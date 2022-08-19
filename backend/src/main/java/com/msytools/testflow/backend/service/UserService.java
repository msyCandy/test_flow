package com.msytools.testflow.backend.service;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.msytools.testflow.backend.api.user.form.LoginForm;
import com.msytools.testflow.backend.api.user.form.RegForm;
import com.msytools.testflow.backend.api.user.vo.LoginVo;
import com.msytools.testflow.backend.common.exception.ClientException;
import com.msytools.testflow.backend.dao.OrganizationUserDao;
import com.msytools.testflow.backend.dao.UserDao;
import com.msytools.testflow.backend.entity.OrganizationUserEntity;
import com.msytools.testflow.backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganizationUserDao organizationUserDao;

    /**
     * 注册用户
     */
    public void reg(RegForm form) {
        UserEntity user = userDao.selectOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getAccount, form.getAccount()));
        if (user != null) {
            throw new ClientException("已有此账户");
        }
        user = new UserEntity();
        user.setAccount(form.getAccount());
        user.setPassword(form.getPassword());
        user.setName(form.getName());

        userDao.insert(user);
    }

    /**
     * 用户登录
     */
    public LoginVo login(LoginForm form) {
        UserEntity user = userDao.selectOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getAccount, form.getAccount())
                .eq(UserEntity::getPassword, form.getPassword()));
        if (user == null) {
            throw new ClientException("用户名或密码错误");
        }

        //生成token
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", user.getId());
        tokenMap.put("createTime", System.currentTimeMillis());
        String token = JWTUtil.createToken(tokenMap, ("testFlow").getBytes());

        //查询组织信息
        List<LoginVo.OrgVo> orgList = organizationUserDao.getUserOrg(user.getId()).stream().map(LoginVo.OrgVo::mapping).collect(Collectors.toList());

        LoginVo vo = new LoginVo();
        vo.setToken(token);
        vo.setOrgList(orgList);

        return vo;
    }

    /**
     * 修改用户的密码
     * @param userId
     * @param password
     */
    public void updatePassword(int userId, String password) {
        userDao.updatePassword(userId, password);
    }
}
