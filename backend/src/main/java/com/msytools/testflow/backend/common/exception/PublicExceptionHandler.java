package com.msytools.testflow.backend.common.exception;

import com.msytools.testflow.backend.entity.dto.RespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class PublicExceptionHandler {
    @ExceptionHandler(value = {BindException.class})
    public RespVo bindException(BindException e){
        String errorMsg = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())
                .toString();
        errorMsg = errorMsg.substring(1,errorMsg.length()-1);
        String[] strings = errorMsg.split(",");
        return RespVo.fail(strings[0]);
    }

    @ExceptionHandler(value = {NoLoginException.class})
    public RespVo noLoginException(){
        return RespVo.noLogin();
    }

    @ExceptionHandler(value = {ClientException.class})
    public RespVo paramException(ClientException e){
        return RespVo.fail(e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public RespVo nullPointerException(Exception e) {
        log.error("系统异常 -> {}", e.getMessage());
        e.printStackTrace();
        return RespVo.err();
    }
}
