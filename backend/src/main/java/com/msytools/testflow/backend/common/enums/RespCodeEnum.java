package com.msytools.testflow.backend.common.enums;

public enum RespCodeEnum {
    success(0),
    fail(1),
    err(2),
    noLogin(3);

    private int code;

    RespCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
