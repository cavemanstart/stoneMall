package com.stone.log.anotation;

import com.stone.log.enums.Operator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    public String title();//模块名称
    public Operator operator() default Operator.MANAGER;//操作人类别
    public int businessType();//业务类型
    public boolean isSaveRequestData() default true;//是否保存请求的参数
    public boolean isSaveResponseData() default true;//是否保存响应的参数

}
