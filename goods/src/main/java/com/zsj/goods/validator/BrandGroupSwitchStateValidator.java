package com.zsj.goods.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class BrandGroupSwitchStateValidator {
    @NotEmpty(message = "ID不能为空！")
    @Pattern(regexp = "[1-9]|([1-9]\\d+)", message = "ID必须是正整数！")
    private String id;

    @NotEmpty(message = "状态值不能为空！")
    @Pattern(regexp = "\\d", message = "状态值为0或者1！")
    @Range(min = 0, max = 1, message = "状态值为0或者1")
    private String isDisabled;
}
