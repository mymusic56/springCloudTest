package com.zsj.goods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品价格导入明细
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_import_price_detail")
public class ImportPriceDetail extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 是否有效 1：是 0：否
     */
    private Boolean isValid;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 主数据物料编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 记录类型 0：客户价格
     */
    private Integer type;

    /**
     * 导入的价格
     */
    private BigDecimal price;

    /**
     * 提示文字
     */
    private String message;

    /**
     * 是否0为执行 1已执行
     */
    private Integer isExecute;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    /**
     * 执行时间
     */
    private Integer executedAt;


}
