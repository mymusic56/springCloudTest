package com.zsj.goods.service.impl;

import com.zsj.goods.entity.Brand;
import com.zsj.goods.mapper.BrandMapper;
import com.zsj.goods.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品品牌 服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
