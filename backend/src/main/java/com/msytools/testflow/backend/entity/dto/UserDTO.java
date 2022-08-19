package com.msytools.testflow.backend.entity.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String token;

    private Integer id;
    private String account;
    private String name;
}
