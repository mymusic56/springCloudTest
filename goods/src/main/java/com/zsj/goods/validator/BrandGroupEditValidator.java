package com.zsj.goods.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class BrandGroupEditValidator {
    private String id = "0";

    @NotBlank(message = "系列名称不能为空")
    @Length(min = 2, max = 50, message = "系列名称字数必须大于等于2，小于等于50")
    private String groupName;

    @NotBlank(message = "排序字段不能为空")
    @Pattern(regexp = "\\d+", message = "排序字段必须是正整数！")
    private String ordinal;

    @NotBlank(message = "图片信息不能为空")
    private String img;
}
