package com.zsj.goods.vo;

public class BrandVo {
    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String img;

    /**
     * 品牌描述
     */
    private String brandDesc;

    /**
     * 排序号 数字越大越靠前
     */
    private Integer ordinal;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 是否禁用，1：禁用，0：未禁用
     */
    private Integer isDisabled;

    /**
     * 产品系列ID
     */
    private Integer groupId;
}
