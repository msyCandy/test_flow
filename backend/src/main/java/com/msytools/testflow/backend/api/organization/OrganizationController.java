package com.msytools.testflow.backend.api.organization;

import com.msytools.testflow.backend.api.organization.form.AddUserForm;
import com.msytools.testflow.backend.api.organization.form.CreateForm;
import com.msytools.testflow.backend.api.organization.form.GetUserListForm;
import com.msytools.testflow.backend.api.organization.form.RemoveUserForm;
import com.msytools.testflow.backend.api.organization.vo.GetUserListVo;
import com.msytools.testflow.backend.common.annotations.NeedLogin;
import com.msytools.testflow.backend.common.enums.RoleEnum;
import com.msytools.testflow.backend.common.exception.ClientException;
import com.msytools.testflow.backend.entity.dto.RespVo;
import com.msytools.testflow.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@NeedLogin
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 创建组织
     */
    @PostMapping("/create")
    public RespVo<Integer> create(@RequestBody @Validated CreateForm form) {
        Integer orgId = organizationService.create(form);
        return RespVo.success(orgId);
    }

    /**
     * 添加用户到组织内
     */
    @PostMapping("/add_user")
    public RespVo addUser(@RequestBody @Validated AddUserForm form) {
        if (!RoleEnum.checkRoleId(form.getRoleId())) {
            throw new ClientException("roleId不合法");
        }
        organizationService.addUser(form);
        return RespVo.success();
    }

    /**
     * 将用户移出组织
     */
    @PostMapping("/remove_user")
    public RespVo removeUser(@RequestBody @Validated RemoveUserForm form) {
        organizationService.removeUser(form);
        return RespVo.success();
    }

    /**
     * 获取组织内的成员
     */
    @GetMapping("/get_user_list")
    public RespVo<List<GetUserListVo>> getUserList(@Validated GetUserListForm form) {
        if (form.getRoleId() != null && !RoleEnum.checkRoleId(form.getRoleId())) {
            throw new ClientException("roleId不合法");
        }
        List<GetUserListVo> voList = organizationService.getUserList(form).stream().map(GetUserListVo::mapping).collect(Collectors.toList());
        return RespVo.success(voList);
    }
}
