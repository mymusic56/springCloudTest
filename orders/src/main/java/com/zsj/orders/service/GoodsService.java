package com.zsj.orders.service;

import com.zsj.orders.service.fallback.GoodsServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "goods-service", fallbackFactory = GoodsServiceFallbackFactory.class)
public interface GoodsService {
    @GetMapping("/goods/info")
    public String info(@RequestParam("goodsId") int id);

    @PostMapping("/goods/updateStock")
    String updateStock(@RequestParam("goodsId") int goodsId, @RequestParam("num") int num);
}
