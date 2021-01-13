package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zsj.goods.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品系列
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_brand_group")
@ApiModel(value = "产品系列")
public class BrandGroup extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 产品系列名称
     */
    @ApiModelProperty(value = "系列名称")
    private String groupName;

    /**
     * 图片
     */
    @ApiModelProperty(value = "系列图片")
    private String img;

    /**
     * 展示图片
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "系列图片（展示）")
    private String imgPath;

    /**
     * 排序号 数字越大越靠前
     */
    @ApiModelProperty(value = "排序")
    private Integer ordinal;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Integer updatedAt;

    /**
     * 是否删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

    /**
     * 状态：0：启用，1：禁用
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否禁用")
    private Integer isDisabled;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否删除（中文）")
    private String isDisabledStr;
}
