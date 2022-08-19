package com.msytools.testflow.backend.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogin {
    boolean needLogin() default true;
}

