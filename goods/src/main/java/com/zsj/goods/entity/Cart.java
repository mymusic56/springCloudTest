package com.zsj.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_cart")
public class Cart extends Base {

    private static final long serialVersionUID=1L;

    private Integer goodsId;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 数量：单位（托）
     */
    private Integer unitTorr;

    /**
     * 数量：单位（箱）
     */
    private Integer unitCon;

    /**
     * 数量：单位（罐）
     */
    private Integer num;

    /**
     * 商户ID
     */
    private Integer customerId;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 分类ID
     */
    private Integer catId;

    /**
     * 添加购物车时的session_id
     */
    private String sessionId;

    /**
     * 是否选择
     */
    private Integer isSelect;

    /**
     * 更新时间
     */
    private Integer updatedAt;


}
