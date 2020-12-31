package com.zsj.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private int id = 0;
    private String orderSn;
    private int userId;
    private String address;
    private double amount;
    private int createdAt;
}
