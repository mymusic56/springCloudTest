package com.zsj.orders.mybatis.mapper;

import com.zsj.orders.dto.OrderDTO;
import com.zsj.orders.entity.OrdersEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {

    public int insert(OrderDTO orderDTO);

    List<OrdersEntity> listOrderWithGoodsByOrderSn(@Param("orderSnList") List<String> orderSnList);
}
