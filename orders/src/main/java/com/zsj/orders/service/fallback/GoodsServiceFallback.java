package com.zsj.orders.service.fallback;

import com.zsj.lib.utils.ResultData;
import com.zsj.orders.service.GoodsService;

import java.util.Map;

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

    @Override
    public ResultData goodsList(Map<String, Object> param) {
        return ResultData.error(501, "goodsList 熔断" + throwable.getMessage());
//        return "{\"status\":501,\"message\":\"goodsList 熔断" + throwable.getMessage() +"\"}";
    }
}
