package com.zsj.goods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.zsj.common.utils.DateUtil;
import com.zsj.common.utils.ResultUtil;
import com.zsj.goods.exception.sentinel.HandleGoodsException;
import com.zsj.goods.entity.GoodsEntity;
import com.zsj.goods.mybatis.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping("/info")
    @SentinelResource(
            //不设置 blockHandlerClass 会调用当前类中的 handleInfoException
//            blockHandlerClass = HandleGoodsException.class,
            blockHandler = "handleInfoException"
    )
    public String info(@RequestParam("goodsId") int id) {

        if (DateUtil.getTimestamp() % 2 == 0) {
//            throw new RuntimeException("模拟异常发生！");
        }
        GoodsEntity entity = goodsMapper.findById(id);
        if (entity == null) {
            return ResultUtil.json(500, "商品不存在！", null);
        }

        return ResultUtil.json(200, "成功", entity);
    }

    @PostMapping("updateStock")
    public String updateStock(@RequestParam("goodsId") int goodsId, @RequestParam("num") int num) throws Exception {
        GoodsEntity entity = goodsMapper.findById(goodsId);
        if (entity == null) {
            return ResultUtil.json(500, "商品不存在", null);
        }

        if (entity.getNum() < num) {
            return ResultUtil.json(500, "商品库存不足，剩余" + entity.getNum(), null);
        }

        entity.setNum(entity.getNum() - num);
        entity.setUpdatedAt(DateUtil.getTimestamp());
        goodsMapper.update(entity);

        return ResultUtil.json(200, "成功", null);
    }


    public String handleInfoException(int id, BlockException b) {
        System.out.println("商品详情接口，流量超过限制！{exception}");
        return ResultUtil.json(500, "商品详情接口，流量超过限制{exception}！", null);
    }
}
