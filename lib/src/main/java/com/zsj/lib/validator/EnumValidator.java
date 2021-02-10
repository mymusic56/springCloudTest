package com.zsj.lib.validator;

import com.zsj.lib.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zhangshangji
 * @since 2021/2/5 18:43
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {
    @Override
    public void initialize(Enum constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
