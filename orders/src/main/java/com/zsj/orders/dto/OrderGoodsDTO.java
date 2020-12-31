package com.zsj.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGoodsDTO {
    private int id = 0;
    private int orderId;
    private int goodsId;
    private String goodsSn;
    private String goodsName;
    private int num;
}
