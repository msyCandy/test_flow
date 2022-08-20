package com.msytools.testflow.backend.api.organization.vo;

import com.msytools.testflow.backend.entity.dto.OrganizationUserDTO;
import lombok.Data;

@Data
public class GetUserListVo {
    private Integer userId;
    private String name;
    private Integer roleId;

    public static GetUserListVo mapping(OrganizationUserDTO dto) {
        GetUserListVo vo = new GetUserListVo();
        vo.setUserId(dto.getUserId());
        vo.setName(dto.getUserName());
        vo.setRoleId(dto.getRoleId());

        return vo;
    }
}
