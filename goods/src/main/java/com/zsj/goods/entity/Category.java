package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_category")
public class Category extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 分类图标
     */
    private String ioc;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 描述
     */
    private String catDesc;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 排序号越大越靠前
     */
    private Boolean ordinal;

    /**
     * 是否显示首页 0为否 1为是
     */
    private Boolean showIndex;

    /**
     * 筛选属性ID 用英文逗号分隔
     */
    private String filterAttr;

    /**
     * 状态: 0启用1禁用
     */
    private Boolean isDisabled;

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

    /**
     * 是否删除 0为否 1为是
     */
    private Integer isDeleted;


}
