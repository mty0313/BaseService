package top.mty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author mty
 * @Date 2021/8/2 15:20
 * @Description 自定义数据校验注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DefineAnnotation {
    // 是否必填
    boolean isRequire() default false;

    // 默认值实现
    String defaultValue() default "";

    // 值域正则
    String regular() default "";

    // 本类的其他对象存在时不能为空, 多个以","隔开
    String notNullRegular() default "";
}
