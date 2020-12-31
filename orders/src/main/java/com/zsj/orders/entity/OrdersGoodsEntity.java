package com.zsj.orders.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersGoodsEntity {
    private int id;
    private int orderId;
    private String goodsSn;
    private String goodsName;
    private int num;
}
