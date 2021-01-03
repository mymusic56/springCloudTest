package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品相册
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_goods_album")
public class GoodsAlbum extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 排序号 数字越大越靠前
     */
    private Integer ordinal;

    /**
     * 图片类型，1：logo，2：详情相册
     */
    private Integer imgType;

    /**
     * 图片名称
     */
    private String imgTitle;

    /**
     * 图片相对路径
     */
    private String img;

    /**
     * 展示图片
     */
    @TableField(exist = false)
    private String imgPath;

    /**
     * 图片描述
     */
    private String imgDescription;

    /**
     * 修改时间
     */
    private Integer updatedAt;

    /**
     * 是否删除
     */
    private Boolean isDeleted;


}
