package com.zsj.goods.service.impl;

import com.zsj.common.utils.ToolUtil;
import com.zsj.goods.config.Config;
import com.zsj.goods.entity.*;
import com.zsj.goods.mapper.*;
import com.zsj.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.goods.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售商品表   服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    GoodsAlbumMapper goodsAlbumMapper;

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    BrandMapper brandMapper;

    @Resource
    BrandGroupMapper brandGroupMapper;

    @Autowired
    Config config;

    public GoodsVo getInfo(Serializable id){
        GoodsVo goodsVo = new GoodsVo();

        Goods goods = this.getById(id);
        if (goods == null) {
            return null;
        }

        goodsVo.setId(goods.getId());
        goodsVo.setGoodsName(goods.getGoodsName());
        goodsVo.setBasePrice(goods.getBasePrice());
        goodsVo.setGoodsCode(goods.getGoodsCode());
        goodsVo.setGoodsImg(goods.getGoodsImg());
        goodsVo.setGoodsWeight(goods.getGoodsWeight());
        goodsVo.setIsOnsale(goods.getIsOnsale());
        goodsVo.setOrdinal(goods.getOrdinal());
        goodsVo.setMaxNum(goods.getMaxNum());
        goodsVo.setMinNum(goods.getMinNum());
        goodsVo.setSaleNum(goods.getSaleNum());
        goodsVo.setUnitConTin(goods.getUnitConTin());
        goodsVo.setUnitTorrTin(goods.getUnitTorrTin());
        goodsVo.setGoodsImgPath(ToolUtil.getRealUrl(config.getImgHost(), goods.getGoodsImg()));

        //获取图片
        List<GoodsAlbum> albums = goodsAlbumMapper.selectByGoodsId(goods.getId());
        Map<String, Object> albums2 = new HashMap<>();
        albums.forEach(item -> {
            albums2.put("id", item.getId());
            albums2.put("img", item.getImg());
            albums2.put("imgPath", ToolUtil.getRealUrl(config.getImgHost(), item.getImg()));
        });

        goodsVo.setGoodsAlbum(albums2);
        //获取品牌
        Brand brand = brandMapper.selectById(goods.getBrandId());
        brand.setImgPath(ToolUtil.getRealUrl(config.getImgHost(), brand.getImg()));

        //获取系列
        BrandGroup brandGroup = brandGroupMapper.selectById(brand.getGroupId());
        brandGroup.setImgPath(ToolUtil.getRealUrl(config.getImgHost(), brandGroup.getImg()));

        //获取分类
        Category category = categoryMapper.getById(goods.getCatId());

        goodsVo.setBrand(brand);
        goodsVo.setBrandGroup(brandGroup);
        goodsVo.setCategory(category);

        return goodsVo;
    }
}
