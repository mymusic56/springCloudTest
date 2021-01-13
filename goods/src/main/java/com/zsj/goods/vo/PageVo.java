package com.zsj.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class PageVo<T> {
    @ApiModelProperty("当前页")
    long currentPage;
    @ApiModelProperty("总页数")
    long pageTotal = 0;
    @ApiModelProperty("总数量")
    long dataTotal = 0;
    @ApiModelProperty("数据数组")
    List<T> list;

}
