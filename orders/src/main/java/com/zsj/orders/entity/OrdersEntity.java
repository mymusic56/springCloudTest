package com.zsj.orders.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersEntity {
    private int id;
    private int userId;
    private String orderSn;
    private int createdAt;
    private int updatedAt;
    private String address;
    private double amount;
    private List<OrdersGoodsEntity> goodsList;
}
