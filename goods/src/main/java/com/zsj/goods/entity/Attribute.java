package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 属性表
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_attribute")
public class Attribute extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 属性类型：1单选 属性，2复选属性
     */
    private Integer attrType;

    /**
     * 排序
     */
    private Integer ordinal;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 修改时间
     */
    private Integer updatedAt;

    /**
     * 是否删除：0否，1删除
     */
    private Integer isDeleted;

    /**
     * 是否停用：0否 ，1是
     */
    private Integer isDisabled;


}
