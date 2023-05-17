package com.lmyxlf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 使用 aop 实现 redis 手动序列化
 *
 * @author lmy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisAnnotation {
    /**
     * redis 存储的键名
     *
     * @return 无描述
     */
    String[] value();
}