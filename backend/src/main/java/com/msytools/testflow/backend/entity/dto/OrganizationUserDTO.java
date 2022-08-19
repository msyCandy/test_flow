package com.msytools.testflow.backend.entity.dto;

import lombok.Data;

@Data
public class OrganizationUserDTO {
    private Integer orgId;
    private String orgName;
    private Integer roleId;
}
