package com.zsj.lib.validator;

import com.zsj.lib.annotation.IsInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 参考 javax.validation.ConstraintValidator
 *      org.hibernate.validator.internal.constraintvalidators.bv
 *
 * @author zhangshangji
 * @since 2021/2/5 17:19
 */
public class IsIntegerValidator implements ConstraintValidator<IsInteger, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("o = " + o);
        Pattern pattern = Pattern.compile("^[-|+]?[\\d]*$");
        return pattern.matcher(String.valueOf(o)).matches();
    }
}
