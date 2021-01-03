package com.zsj.goods.service.impl;

import com.zsj.goods.entity.Cart;
import com.zsj.goods.mapper.CartMapper;
import com.zsj.goods.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author zhangshengji
 * @since 2021-01-01
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
