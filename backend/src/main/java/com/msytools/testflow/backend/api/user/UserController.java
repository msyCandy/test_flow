package com.msytools.testflow.backend.api.user;

import cn.hutool.crypto.digest.DigestUtil;
import com.msytools.testflow.backend.api.user.form.LoginForm;
import com.msytools.testflow.backend.api.user.form.RegForm;
import com.msytools.testflow.backend.api.user.form.UpdatePasswordForm;
import com.msytools.testflow.backend.api.user.vo.LoginVo;
import com.msytools.testflow.backend.common.annotations.NeedLogin;
import com.msytools.testflow.backend.entity.dto.RespVo;
import com.msytools.testflow.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 注册用户
     */
    @PostMapping("/reg")
    public RespVo reg(@RequestBody @Validated RegForm form) {
        form.setPassword(DigestUtil.md5Hex(form.getPassword()));
        userService.reg(form);
        return RespVo.success();
    }

    @PostMapping("/login")
    public RespVo login(@RequestBody @Validated LoginForm form) {
        form.setPassword(DigestUtil.md5Hex(form.getPassword()));
        LoginVo loginVo = userService.login(form);
        return RespVo.success(loginVo);
    }

    @NeedLogin
    @PostMapping("/update_password")
    public RespVo updatePassword(@RequestBody @Validated UpdatePasswordForm form) {
        form.setPassword(DigestUtil.md5Hex(form.getPassword()));
        Integer userId = (Integer) request.getAttribute("userId");
        userService.updatePassword(userId, form.getPassword());
        return RespVo.success();
    }

}
