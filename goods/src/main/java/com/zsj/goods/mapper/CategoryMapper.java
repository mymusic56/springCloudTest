package com.zsj.goods.mapper;

import com.zsj.goods.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
public interface CategoryMapper extends BaseMapper<Category> {
    Category getById(int id);
}
