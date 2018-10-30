package com.fh.api.common;

import java.lang.annotation.*;

/**
 * @类描述：(自定义注解类)
 * @作者        ：张鹿阁
 * @邮箱        : 200766243@qq.com
 * @创建日期    ：2018年09月30日 16:20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {
    int nowCount();
    int interval();
}
