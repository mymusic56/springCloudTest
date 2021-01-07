package com.zsj.goods.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class BrandGroupDeleteValidator {
    @NotNull(message = "ID不能为空！")
    @Positive(message = "ID必须是正整数！")
    private String id;

//    @NotBlank
//    @Size(min = 0, max = 1, message = "请输入正确的状态值")
//    private int isDisabled;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的手机号")
    private String mobile;
}
