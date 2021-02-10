package com.zsj.goods.validator;

import com.zsj.lib.annotation.Enum;
import com.zsj.lib.annotation.IsInteger;
import com.zsj.lib.enums.StatusRemark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@ApiModel(value = "产品系列BrandGroupDTO", description = "产品系列信息封装，用于接口传参")
public class BrandGroupDeleteValidator {
    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(name = "id", value = "系列ID", required = true)
    @IsInteger(message = "请填入数字")
    private String id;

//    @NotBlank
//    @Size(min = 0, max = 1, message = "请输入正确的状态值")
//    private int isDisabled;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的手机号")
    @ApiModelProperty(name = "mobile", value = "手机号,无效参数", required = false)
    private String mobile;
}
