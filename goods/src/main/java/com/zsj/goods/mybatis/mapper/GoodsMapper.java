package com.zsj.goods.mybatis.mapper;

import com.zsj.goods.entity.GoodsEntity;

public interface GoodsMapper {

    public GoodsEntity findById(int id);

    public int update(GoodsEntity goodsEntity);
}
