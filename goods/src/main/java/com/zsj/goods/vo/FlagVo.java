package com.zsj.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

@Data
@ApiModel
public class FlagVo {
    @ApiModelProperty(value = "执行结果,\"0:未处理，1：执行成功，-1：执行失败\"", example = "1", allowableValues = "1,0,-1")
    int flag = 0;
}