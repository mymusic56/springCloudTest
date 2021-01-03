package com.zsj.goods.vo;

import lombok.Data;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
public class CategoryVo {

    private Integer id;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 分类图标
     */
    private String ioc;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 是否显示首页 0为否 1为是
     */
    private Boolean showIndex;

    /**
     * 状态: 0启用1禁用
     */
    private Boolean isDisabled;

    private String isDisabledStr;

    /**
     * 分类级别 1为顶级 2为2级 依次类推
     */
    private Integer leave;

    /**
     * 到顶级的parent_id用逗号隔  如当前分类id为4   父级id为3   2,3,4
     */
    private String parentarr;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    private String updatedAtStr;

}
