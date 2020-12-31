package com.zsj.orders.service.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GoodsServiceFallbackFactory implements FallbackFactory<GoodsServiceFallback> {
    @Override
    public GoodsServiceFallback create(Throwable throwable) {
        return new GoodsServiceFallback(throwable);
    }
}
