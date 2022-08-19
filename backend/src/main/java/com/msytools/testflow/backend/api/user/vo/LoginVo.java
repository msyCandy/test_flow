package com.msytools.testflow.backend.api.user.vo;

import com.msytools.testflow.backend.dao.OrganizationUserDao;
import com.msytools.testflow.backend.entity.dto.OrganizationUserDTO;
import lombok.Data;

import java.util.List;

@Data
public class LoginVo {
    private String token;

    private List<OrgVo> orgList;

    @Data
    public static class OrgVo {
        private Integer orgId;
        private String orgName;
        private Integer roleId;

        public static OrgVo mapping(OrganizationUserDTO dto) {
            OrgVo result = new OrgVo();
            result.setOrgId(dto.getOrgId());
            result.setOrgName(dto.getOrgName());
            result.setRoleId(dto.getRoleId());

            return result;
        }
    }
}
