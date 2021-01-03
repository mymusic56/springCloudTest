package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 属性子项
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_attribute_item")
public class AttributeItem extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 属性ID
     */
    private Integer attrId;

    /**
     * 属性子项名称
     */
    private String itemName;

    /**
     * 修改时间
     */
    private Integer updatedAt;

    /**
     * 是否删除：0否，1删除
     */
    private Integer isDeleted;


}
