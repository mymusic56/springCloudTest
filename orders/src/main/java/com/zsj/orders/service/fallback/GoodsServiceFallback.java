package com.zsj.orders.service.fallback;

import com.zsj.orders.service.GoodsService;

public class GoodsServiceFallback implements GoodsService {

    private Throwable throwable;

    GoodsServiceFallback (Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String info(int id) {
        return "{\"status\":501,\"message\":\"info熔断" + throwable.getMessage() +"\"}";
    }

    @Override
    public String updateStock(int goodsId, int num) {
        return "{\"status\":501,\"message\":\"updateStock熔断" + throwable.getMessage() +"\"}";
    }
}
