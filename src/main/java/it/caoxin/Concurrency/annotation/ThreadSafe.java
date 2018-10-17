package it.caoxin.Concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 【标识线程安全】的写法
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Target(ElementType.TYPE)//注解写在类上面
@Retention(RetentionPolicy.SOURCE)//编译
//@Retention(RetentionPolicy.CLASS)//字节码
//@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface ThreadSafe {
    String value() default "";
}
