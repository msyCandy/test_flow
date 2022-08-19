package com.msytools.testflow.backend.entity.dto;

import com.msytools.testflow.backend.common.enums.RespCodeEnum;
import lombok.Data;

@Data
public class RespVo<T> {
    private Integer code;
    private String msg = "";
    private T data;

    private RespVo() {}

    public static final RespVo success() {
        RespVo vo = new RespVo();
        vo.setCode(RespCodeEnum.success.getCode());

        return vo;
    }

    public static final <T> RespVo<T> success(T data) {
        RespVo vo = new RespVo();
        vo.setCode(RespCodeEnum.success.getCode());
        vo.setData(data);

        return vo;
    }

    public static final RespVo fail(String msg) {
        RespVo vo = new RespVo();
        vo.setCode(RespCodeEnum.fail.getCode());
        vo.setMsg(msg);

        return vo;
    }

    public static final RespVo err() {
        RespVo vo = new RespVo();
        vo.setCode(RespCodeEnum.err.getCode());
        vo.setMsg("系统异常，请稍后再试");

        return vo;
    }

    public static final RespVo noLogin() {
        RespVo vo = new RespVo();
        vo.setCode(RespCodeEnum.noLogin.getCode());

        return vo;
    }
}
