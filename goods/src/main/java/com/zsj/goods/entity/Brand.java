package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品品牌
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_brand")
public class Brand extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String img;

    /**
     * 展示图片
     */
    @TableField(exist = false)
    private String imgPath;

    /**
     * 品牌描述
     */
    private String brandDesc;

    /**
     * 品牌官网地址
     */
    private String siteUrl;

    /**
     * 排序号 数字越大越靠前
     */
    private Integer ordinal;

    /**
     * 是否首页推荐 0为否 1为是
     */
    private Integer showIndex;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer updatedAt;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 是否禁用，1：禁用，0：未禁用
     */
    private Integer isDisabled;

    /**
     * 国家ID
     */
    private Integer countyId;

    /**
     * 产品系列ID
     */
    private Integer groupId;


}
