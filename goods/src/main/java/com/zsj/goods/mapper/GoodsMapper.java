package com.zsj.goods.mapper;

import com.zsj.goods.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 销售商品表   Mapper 接口
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    public Goods findById(int id);
}
