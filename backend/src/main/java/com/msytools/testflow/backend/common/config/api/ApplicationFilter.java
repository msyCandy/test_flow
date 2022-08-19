package com.msytools.testflow.backend.common.config.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ApplicationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        long t = System.currentTimeMillis();
        chain.doFilter(req, res);
        long t2 = System.currentTimeMillis();
        long time = t2 - t;
        if (time > 5000) {
            log.warn("接口请求耗时较长，接口【{}】，耗时【{}】", request.getRequestURI(), time);
        }
    }
}