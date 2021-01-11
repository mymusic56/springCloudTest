package com.zsj.goods.service;

import com.zsj.lib.utils.ResultData;
import com.zsj.goods.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.goods.entity.GoodsAlbum;
import com.zsj.goods.vo.GoodsVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售商品表   服务类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
public interface IGoodsService extends IService<Goods> {
    GoodsVo getInfo(Serializable id);

    ResultData checkGoodsParam(Map<String, Object> param, int id);

    ResultData checkGoodsAlbum(Map<String, Object> param, int goodsId);

    ResultData checkMainDataGoodsCode(Map<String, Object> param);

    int saveGoods(Goods goods, Map<String, Object> param, int id);

    int saveGoodsAlbum(int goodsId, List<GoodsAlbum> goodsAlbumList);
}
