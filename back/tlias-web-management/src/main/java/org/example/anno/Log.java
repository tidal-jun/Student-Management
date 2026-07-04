package org.example.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)     //元注解，表示该注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) //表示该注解在运行时保留
public @interface Log {
}
