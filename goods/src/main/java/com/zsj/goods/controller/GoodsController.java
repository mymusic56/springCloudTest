package com.zsj.goods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zsj.common.utils.DateUtil;
import com.zsj.common.utils.ResultData;
import com.zsj.goods.entity.Goods;
import com.zsj.goods.exception.LogicException;
import com.zsj.goods.exception.sentinel.HandleGoodsException;
import com.zsj.goods.entity.GoodsEntity;
import com.zsj.goods.mapper.GoodsMapper;
import com.zsj.goods.service.impl.GoodsServiceImpl;
import com.zsj.goods.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/goods/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping(value = "/info")
    @SentinelResource(
            //不设置 blockHandlerClass 会调用当前类中的 handleInfoException
//            blockHandlerClass = HandleGoodsException.class,
            blockHandler = "handleInfoException"
    )
    public ResultData info(@RequestParam("goodsId") int id) throws LogicException {

        if (DateUtil.getTimestamp() % 2 == 0) {
            throw new LogicException("模拟异常发生！");
        }
        GoodsVo goodsVo = goodsService.getInfo(id);

        if (goodsVo == null) {
            return ResultData.success("商品不存在");
        }
        return ResultData.success("success", goodsVo);
    }

    @GetMapping(value = "list")
    @SentinelResource(
            //不设置 blockHandlerClass 会调用当前类中的 handleInfoException
//            blockHandlerClass = HandleGoodsException.class,
            blockHandler = "handleInfoException"
    )
    public ResultData getList(@RequestParam int id) throws LogicException {

        if (DateUtil.getTimestamp() % 2 == 0) {
            throw new LogicException("模拟异常发生！");
        }
        GoodsVo goodsVo = goodsService.getInfo(id);

        if (goodsVo == null) {
            return ResultData.success("商品不存在");
        }
        return ResultData.success("success", goodsVo);
    }


    @PostMapping("updateStock")
    public ResultData updateStock(@RequestParam("goodsId") int goodsId, @RequestParam("num") int num) throws Exception {
        Goods entity = goodsMapper.selectById(goodsId);
        if (entity == null) {
            return ResultData.error(500, "商品不存在", null);
        }

        if (entity.getNum() < num) {
            return ResultData.error(500, "商品库存不足，剩余" + entity.getNum());
        }

        entity.setNum(entity.getNum() - num);
        entity.setUpdatedAt(DateUtil.getTimestamp());
        goodsMapper.updateById(entity);

        return ResultData.success();
    }


    public ResultData handleInfoException(int id, BlockException b) {
        System.out.println("商品详情接口，流量超过限制！{exception}");
        return ResultData.error(500, "商品详情接口，流量超过限制{exception}！");
    }
}
