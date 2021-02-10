package com.zsj.lib.annotation;

import com.zsj.lib.validator.IsIntegerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshangji
 * @since 2021/2/5 17:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsIntegerValidator.class)
public @interface Enum {
    String message() default "";

    Class<?> checkMap();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
