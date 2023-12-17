package com.stone.log.anotation;

import com.stone.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//作用在类，接口等上面
@Retention(RetentionPolicy.RUNTIME)//作用域为运行时
@Import(value = LogAspect.class)//通过import注解导入LogAspect类到spring容器
public @interface EnableLogAspect {
}
