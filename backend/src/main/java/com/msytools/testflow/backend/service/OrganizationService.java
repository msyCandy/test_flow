package com.msytools.testflow.backend.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.msytools.testflow.backend.api.organization.form.AddUserForm;
import com.msytools.testflow.backend.api.organization.form.CreateForm;
import com.msytools.testflow.backend.common.enums.RoleEnum;
import com.msytools.testflow.backend.common.exception.ClientException;
import com.msytools.testflow.backend.dao.OrganizationDao;
import com.msytools.testflow.backend.dao.OrganizationUserDao;
import com.msytools.testflow.backend.dao.UserDao;
import com.msytools.testflow.backend.entity.OrganizationEntity;
import com.msytools.testflow.backend.entity.OrganizationUserEntity;
import com.msytools.testflow.backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private OrganizationUserDao organizationUserDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpServletRequest request;

    /**
     * 添加用户进组织
     * @param form
     */
    public void addUser(AddUserForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        UserEntity user = userDao.selectOne(Wrappers.<UserEntity>lambdaQuery()
                .eq(UserEntity::getAccount, form.getAccount()));
        if (user == null) {
            throw new ClientException("此账户为空");
        }
        OrganizationUserEntity hasPermission = organizationUserDao.selectOne(Wrappers.<OrganizationUserEntity>lambdaQuery()
                .eq(OrganizationUserEntity::getOrganizationId, form.getOrgId())
                .eq(OrganizationUserEntity::getUserId, userId)
                .eq(OrganizationUserEntity::getRoleId, RoleEnum.ADMIN.getRoleId()));
        if (hasPermission == null) {
            throw new ClientException("权限不足");
        }
        OrganizationUserEntity userRow = organizationUserDao.selectOne(Wrappers.<OrganizationUserEntity>lambdaQuery()
                .eq(OrganizationUserEntity::getOrganizationId, form.getOrgId())
                .eq(OrganizationUserEntity::getUserId, user.getId()));
        if (userRow != null) {
            throw new ClientException("组织内已存在此用户");
        }
        userRow = new OrganizationUserEntity();
        userRow.setOrganizationId(form.getOrgId());
        userRow.setUserId(user.getId());
        userRow.setRoleId(form.getRoleId());

        try {
            organizationUserDao.insert(userRow);
        } catch (RuntimeException e) {
            throw new ClientException("组织内已存在此用户");
        }
    }

    /**
     * 创建一个组织
     * @param form
     * @return
     */
    public Integer create(CreateForm form) {
        Integer userId = (Integer) request.getAttribute("userId");
        OrganizationEntity org = organizationDao.selectOne(Wrappers.<OrganizationEntity>lambdaQuery()
                .eq(OrganizationEntity::getName, form.getName()));
        if (org != null) {
            throw new ClientException("已有同名组织");
        }
        org = new OrganizationEntity();
        org.setName(form.getName());
        org.setCreateUserId(userId);

        try {
            organizationDao.insert(org);
        } catch (RuntimeException e) {
            //只有可能是name唯一索引冲突
            throw new ClientException("已有同名组织");
        }
        OrganizationUserEntity row = new OrganizationUserEntity();
        row.setOrganizationId(org.getId());
        row.setUserId(userId);
        row.setRoleId(RoleEnum.ADMIN.getRoleId());
        organizationUserDao.insert(row);

        return org.getId();
    }
}
