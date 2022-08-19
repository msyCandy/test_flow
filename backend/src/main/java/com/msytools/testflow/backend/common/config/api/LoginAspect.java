package com.msytools.testflow.backend.common.config.api;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.msytools.testflow.backend.common.annotations.NeedLogin;
import com.msytools.testflow.backend.common.exception.NoLoginException;
import com.msytools.testflow.backend.common.exception.PublicExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LoginAspect {

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PublicExceptionHandler publicExceptionHandler;


    @Pointcut("execution(public * com.msytools.testflow.backend.api.*.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        boolean needCheckLogin = false;

        String methodName = joinPoint.getSignature().getName();
        Class aClass = joinPoint.getSignature().getDeclaringType();
        NeedLogin needLogin = null;

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method: methods) {
            if (method.getName().equals(methodName)) {
                needLogin = method.getAnnotation(NeedLogin.class);
                if (needLogin != null && needLogin.needLogin()) {
                    needCheckLogin = true;
                    break;
                }
            }
        }
        if (needLogin == null) {
            needLogin = (NeedLogin) aClass.getAnnotation(NeedLogin.class);
            if (needLogin != null && needLogin.needLogin()) {
                needCheckLogin = true;
            }
        }


        //获取用户ip
        String ip = null;
        String token = null;
        if (request != null) {
            ip = getRemoteHost(request);
            token = request.getHeader("token");
        }

        //获取用户id
        int userId = 0;
        //通过token取值
        try {
            if (token != null && JWTUtil.verify(token, ("msytools" + active).getBytes())) {
                JWT jwt = JWTUtil.parseToken(token);
                long time = System.currentTimeMillis() - (long) jwt.getPayload("createTime");
                    //token创建的半小时内不检查IP变化
                    if (ip.equals(jwt.getPayload("ip")) || time < 30 * 60 * 1000) {
                    userId = (int) jwt.getPayload("userId");
                    request.getSession().setAttribute("userId", userId);
                }
            }
        } catch (Exception e) {
        }


        request.setAttribute("userId", userId);
        request.setAttribute("ip", ip);
        if (needCheckLogin && userId == 0) {
            throw new NoLoginException();
        }
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        }catch (NoLoginException e) {
            return publicExceptionHandler.noLoginException();
        }
    }

    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
