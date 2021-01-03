package com.zsj.goods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品客户价格
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_goods_price")
public class GoodsPrice extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 客户ID
     */
    private Integer goodsId;

    private BigDecimal price;


}
