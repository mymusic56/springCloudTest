package com.zsj.goods.vo;

import com.zsj.goods.entity.Brand;
import com.zsj.goods.entity.BrandGroup;
import com.zsj.goods.entity.Category;
import com.zsj.goods.entity.GoodsAlbum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售商品表  
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Data
public class GoodsVo{

    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 主数据物料规格
     */
    private String goodsCode;

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

    private String updatedAtStr;

    /**
     * 商品主图
     */
    private String goodsImg;

    /**
     * 主图展示路径
     */
    private String goodsImgPath;

    /**
     * 商品描述
     */
    private String goodsDesc;


    /**
     * 排序号 数字越大越靠前
     */
    private Boolean ordinal;

    /**
     * 销售数量（罐数） 提交订单就记数 取消订单 就减去对应的数量
     */
    private Integer saleNum;

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

    /**
     * 相册
     */
    private Map<String, Object> goodsAlbum;

    private BrandGroup brandGroup;

    private Brand brand;

    private Category category;

}
