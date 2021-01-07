package com.zsj.orders.service;

import com.zsj.common.utils.ResultData;
import com.zsj.orders.service.fallback.GoodsServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "goods-service", fallbackFactory = GoodsServiceFallbackFactory.class)
public interface GoodsService {
    @GetMapping("/goods/goods/info")
    String info(@RequestParam("goodsId") int id);

    @PostMapping("/goods/goods/updateStock")
    String updateStock(@RequestParam("goodsId") int goodsId, @RequestParam("num") int num);

    @GetMapping("/goods/goods/list")
    ResultData goodsList(@RequestParam Map<String, Object> param);
}
