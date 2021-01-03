package com.zsj.goods.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
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
public class BrandGroupVo {
    private Integer id;
    /**
     * 产品系列名称
     */
    private String groupName;

    /**
     * 图片
     */
    private String img;

    /**
     * 排序号 数字越大越靠前
     */
    private Integer ordinal;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer updatedAt;

    /**
     * 是否删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    /**
     * 状态：0：启用，1：禁用
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDisabled;


}
