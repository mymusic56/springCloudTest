package com.zsj.goods.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class BrandGroupSwitchStateValidator {
    @NotNull(message = "ID不能为空！")
//    @Positive(message = "必须是正整数！")
    @Range(min = 1, message = "ID必须是正整数！")
    private int id;

    @NotNull(message = "状态值不能为空！")
//    @Max(value = 1, message = "状态值为0或者1")
//    @Min(value = 0, message = "状态值为0或者1")
    @Range(min = 0, max = 1, message = "状态值为0或者1")
    private int isDisabled;
}
