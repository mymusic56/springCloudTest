package com.zsj.goods.mapper;

import com.zsj.goods.entity.GoodsAlbum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品相册 Mapper 接口
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
public interface GoodsAlbumMapper extends BaseMapper<GoodsAlbum> {
    List<GoodsAlbum> selectByGoodsId(int goodsId);

    int insertBatch(List<GoodsAlbum> goodsAlbumList);

    int deleteByIds(List<Integer> idList);
}
