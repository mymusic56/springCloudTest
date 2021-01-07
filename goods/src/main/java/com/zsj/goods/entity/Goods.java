package com.zsj.goods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsj.goods.entity.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 销售商品表  
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dn_goods")
public class Goods extends Base {

    private static final long serialVersionUID=1L;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 货号
     */
    private String goodsSn;

    /**
     * 主数据物料规格
     */
    private String goodsCode;

    /**
     * 商品类型 0单品 1组合商品
     */
    private Integer goodsType;

    /**
     * 分类ID
     */
    private Integer catId;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 规格商品ID
     */
    private Integer gsId;

    /**
     * 上架状态0下架1上架
     */
    private Integer isOnsale;

    /**
     * 起购数
     */
    private Integer minNum;

    /**
     * 限购数 0为不限购
     */
    private Integer maxNum;

    /**
     * 成本价
     */
    private BigDecimal basePrice;

    /**
     * 修改时间
     */
    private Integer updatedAt;

    /**
     * 商品主图
     */
    private String goodsImg;

    /**
     * 商品主图展示图片
     */
    @TableField(exist = false)
    private String goodsImgPath;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 有商品规格 时 对应商品属性表ID
     */
    private Integer goodsAttrId;

    /**
     * 有效期
     */
    private String validityDate;

    /**
     * 模版ID
     */
    private Integer shippingId;

    /**
     * 0正常1删除
     */
    private Integer isDeleted;

    /**
     * 排序号 数字越大越靠前
     */
    private Integer ordinal;

    /**
     * 销售数量（罐数） 提交订单就记数 取消订单 就减去对应的数量
     */
    private Integer saleNum;

    /**
     * 浏览次数
     */
    private Integer clickNum;

    /**
     * 第三方库存
     */
    private Integer goodsNumber;

    /**
     * 第三方冻结库存
     */
    private Integer goodsFrozenNumber;

    /**
     * 商品重量（KG）
     */
    private BigDecimal goodsWeight;

    /**
     * 用户修改购买商品数量步长
     */
    private Integer numStep;

    /**
     * 单位：1托多少听
     */
    private Integer unitTorrTin;

    /**
     * 单位：一箱多少听
     */
    private Integer unitConTin;
}
