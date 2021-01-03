package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_goods_attribute")
public class GoodsAttribute extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 分类ID
     */
    private Integer catId;

    /**
     * 属性ID
     */
    private Integer attrId;

    /**
     * 属性子项ID
     */
    private Integer attrItemId;

    private Boolean isDeleted;


}
