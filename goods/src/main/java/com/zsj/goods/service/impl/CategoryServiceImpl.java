package com.zsj.goods.service.impl;

import com.zsj.goods.entity.Category;
import com.zsj.goods.mapper.CategoryMapper;
import com.zsj.goods.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
