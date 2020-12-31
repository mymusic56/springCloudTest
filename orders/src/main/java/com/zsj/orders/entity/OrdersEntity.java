package com.zsj.orders.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersEntity {
    private int id;
    private int userId;
    private String orderSn;
    private int createdAt;
    private String address;
    private double amount;
}
