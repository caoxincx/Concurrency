package it.caoxin.Concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 并发中不推荐的写法
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {
    String value()default "";
}

