package com.zsj.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.common.client.maindata.MaindDataApiImpl;
import com.zsj.common.client.maindata.bean.GoodsSpecsBean;
import com.zsj.common.handler.CustomConfigHandler;
import com.zsj.common.utils.DateUtil;
import com.zsj.common.utils.ParamUtil;
import com.zsj.common.utils.ResultData;
import com.zsj.common.utils.ToolUtil;
import com.zsj.goods.entity.*;
import com.zsj.goods.mapper.*;
import com.zsj.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.goods.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 销售商品表   服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    GoodsAlbumMapper goodsAlbumMapper;

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    BrandMapper brandMapper;

    @Resource
    BrandGroupMapper brandGroupMapper;

    @Resource
    GoodsMapper goodsMapper;

    @Resource
    MaindDataApiImpl maindDataApi;

    @Autowired
    CustomConfigHandler configHandler;

    public GoodsVo getInfo(Serializable id) {
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
        goodsVo.setGoodsImgPath(ToolUtil.getRealUrl(goods.getGoodsImg()));

        //获取图片
        List<GoodsAlbum> albums = goodsAlbumMapper.selectByGoodsId(goods.getId());
        Map<String, Object> albums2 = new HashMap<>();
        albums.forEach(item -> {
            albums2.put("id", item.getId());
            albums2.put("img", item.getImg());
            albums2.put("imgPath", ToolUtil.getRealUrl(item.getImg()));
        });

        goodsVo.setGoodsAlbum(albums2);
        //获取品牌
        Brand brand = brandMapper.selectById(goods.getBrandId());
        brand.setImgPath(ToolUtil.getRealUrl(brand.getImg()));

        //获取系列
        BrandGroup brandGroup = brandGroupMapper.selectById(brand.getGroupId());
        brandGroup.setImgPath(ToolUtil.getRealUrl(brandGroup.getImg()));

        //获取分类
        Category category = categoryMapper.getById(goods.getCatId());

        goodsVo.setBrand(brand);
        goodsVo.setBrandGroup(brandGroup);
        goodsVo.setCategory(category);

        return goodsVo;
    }

    /**
     * 校验商品品牌、系列、分类信息
     *
     * @param param
     */
    public ResultData checkGoodsParam(Map<String, Object> param, int id) {
        //商品名字是否重复
        Goods g1 = goodsMapper.findIdByGoodsNameExcludeId((String) param.get("goodsName"), id);
        if (g1 != null) {
            return ResultData.error("商品名称已存在！");
        }

        //商品编码是否已存在
        Goods goods = goodsMapper.findIdByGoodsCodeExcludeId((String) param.get("goodsCode"), id);
        if (goods != null && goods.getId() > 0) {
            return ResultData.error("商品编码已存在！");
        }

        return ResultData.success();
    }

    /**
     * 商品图片信息校验
     *
     * @param param
     * @return
     */
    public ResultData checkGoodsAlbum(Map<String, Object> param, int goodsId) {
        //检查商品主图
        String goodsImg = (String) param.getOrDefault("goodsImg", "");
        if (!ToolUtil.checkFileExist(configHandler.getUploadsDir() + goodsImg)) {
            return ResultData.error("请上传商品主图！");
        }

        //检查商品相册图片
        String goodsAlbumStr = (String) param.get("goodsAlbum");
        if (goodsAlbumStr == null || "".equals(goodsAlbumStr)) {
            return ResultData.error("请选择商品图片！");
        }
        String errorMsg = "";
        JSONArray albumArr = JSONObject.parseArray(goodsAlbumStr);

        if (albumArr.size() > 9) {
            return ResultData.error("商品相册图片不能超过9张！");
        }

        List<GoodsAlbum> realList = new ArrayList<>();
        int ordinal = albumArr.size();
        for (int i = 0; i < albumArr.size(); i++) {
            JSONObject jsonObject = albumArr.getJSONObject(i);
            String img = jsonObject.getString("img");
            if (img == null || "".equals(img)) {
                continue;
            }
            if (!img.startsWith("/")) {
                img = "/" + img;
            }
            //校验图片是否存在
            if (!ToolUtil.checkFileExist(configHandler.getUploadsDir() + img)) {
                errorMsg += "文件路径" + img + "不存在！";
                continue;
            }
            GoodsAlbum goodsAlbum = new GoodsAlbum();
            goodsAlbum.setId(Integer.parseInt((String) jsonObject.get("id")));
            goodsAlbum.setGoodsId(goodsId);
            goodsAlbum.setOrdinal(ordinal);//处理排序，按照数据传入的顺序进行排列
            goodsAlbum.setImgType(2);//图片类型
            goodsAlbum.setImg(img);
            goodsAlbum.setCreatedAt(DateUtil.getTimestamp());
            realList.add(goodsAlbum);
            ordinal--;
        }

        if (!errorMsg.equals("")) {
            return ResultData.error(errorMsg);
        }
        return ResultData.success("", realList);
    }

    /**
     * 校验商品编码
     *
     * @param param
     * @return
     */
    public ResultData checkMainDataGoodsCode(Map<String, Object> param) {
        //调用主数据接口查看商品编码是否正确
        String goodsCode = (String) param.get("goodsCode");
        GoodsSpecsBean goodsSpecsBean = maindDataApi.getGoodsSpecsDetail(goodsCode);
        if (goodsSpecsBean == null || goodsSpecsBean.getSpecsId() == null || goodsSpecsBean.getSpecsId().isEmpty()) {
            return ResultData.error("规格编码对应商品不存在！");
        }
        if (!goodsCode.equals(goodsSpecsBean.getSpecsId())) {
            return ResultData.error("规格编码获取主数据规格异常！");
        }
        return ResultData.success();
    }

    @Override
    public int saveGoods(Goods goods, Map<String, Object> param, int id) {

        //检测更新字段
        Map<String, Object> update = ParamUtil.checkUpdatedFiled(goods, param);
        log.info("商品表更新字段：" + JSONObject.toJSONString(update));

        goods.setGoodsCode((String) param.get("goodsCode"));
        goods.setGoodsName((String) param.get("goodsName"));
        goods.setOrdinal((Integer) param.get("ordinal"));
        goods.setCatId(Integer.parseInt((String) param.get("catId")));
        goods.setBrandId(Integer.parseInt((String) param.get("brandId")));
        goods.setIsOnsale(Integer.parseInt((String) param.get("isOnsale")));
        goods.setGoodsImg((String) param.get("goodsImg"));
        goods.setGoodsDesc((String) param.get("goodsDesc"));
        goods.setUnitTorrTin(Integer.parseInt((String) param.get("unitTorrTin")));
        goods.setUnitConTin(Integer.parseInt((String) param.get("unitConTin")));

        if (id > 0) {
            goods.setUpdatedAt(DateUtil.getTimestamp());
            boolean flag = this.updateById(goods);
            log.info("商品表更新结果：" + flag);
        } else {
            goods.setCreatedAt(DateUtil.getTimestamp());
            id = goodsMapper.insert(goods);
            log.info("商品表新增：" + id);
        }

        return id;
    }

    @Override
    public int saveGoodsAlbum(int goodsId, List<GoodsAlbum> newGoodsAlbumList) {

        List<GoodsAlbum> goodsAlbumList = goodsAlbumMapper.selectByGoodsId(goodsId);
        Map<Integer, GoodsAlbum> goodsAlbumListMap = new HashMap<>();
        for (GoodsAlbum entity : goodsAlbumList) {
            goodsAlbumListMap.put(entity.getId(), entity);
        }

        List<Integer> deleteIds = new ArrayList<>();
        List<GoodsAlbum> newAddList = new ArrayList<>();
        List<Integer> updateIds = new ArrayList<>();

        for (int i = 0; i < newGoodsAlbumList.size(); i++) {
            GoodsAlbum goodsAlbum = newGoodsAlbumList.get(i);
            GoodsAlbum tmp = goodsAlbumListMap.getOrDefault(goodsAlbum.getId(), null);
            if (goodsAlbum.getId() == 0 || tmp == null) {
                //没有传入ID，或者不在原列表中，新增
                newAddList.add(goodsAlbum);
                continue;
            }
            if (tmp != null) {
                goodsAlbumListMap.remove(goodsAlbum.getId());//剩下的就是需要删除的
                //更新
                updateIds.add(goodsAlbum.getId());
                goodsAlbum.setUpdatedAt(DateUtil.getTimestamp());
                goodsAlbumMapper.updateById(goodsAlbum);
                continue;
            }
        }
        //删除
        GoodsAlbum tmp;
        for (Map.Entry item:goodsAlbumListMap.entrySet()) {
            tmp = (GoodsAlbum) item.getValue();
            deleteIds.add(tmp.getId());
        }

        //新增图片
        if (newAddList.size() > 0) {
            goodsAlbumMapper.insertBatch(newAddList);
        }
        //删除图片
        if (deleteIds.size() > 0) {
            //配置了逻辑删除，这里调用自定义方法
            goodsAlbumMapper.deleteByIds(deleteIds);
        }
        return 1;
    }
}
