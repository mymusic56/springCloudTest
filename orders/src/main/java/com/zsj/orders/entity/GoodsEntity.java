package com.zsj.orders.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsEntity {
    private int id;
    private String goodsSn;
    private String goodsName;
    private int num;
    private int updatedAt;
    private int createdAt;
}
