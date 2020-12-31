package com.zsj.orders.mybatis.mapper;

import com.zsj.orders.dto.OrderDTO;
import com.zsj.orders.dto.OrderGoodsDTO;

public interface OrdersGoodsMapper {

    public int insert(OrderGoodsDTO orderGoodsDTO);

}
