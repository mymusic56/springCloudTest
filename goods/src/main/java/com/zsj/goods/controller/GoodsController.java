package com.zsj.goods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsj.common.utils.DateUtil;
import com.zsj.common.utils.ParamUtil;
import com.zsj.common.utils.ResultData;
import com.zsj.common.utils.ToolUtil;
import com.zsj.goods.entity.Goods;
import com.zsj.goods.entity.GoodsAlbum;
import com.zsj.goods.exception.LogicException;
import com.zsj.goods.exception.sentinel.HandleGoodsException;
import com.zsj.goods.entity.GoodsEntity;
import com.zsj.goods.mapper.GoodsMapper;
import com.zsj.goods.service.IGoodsService;
import com.zsj.goods.service.impl.GoodsServiceImpl;
import com.zsj.goods.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private IGoodsService goodsService;

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

    /**
     * 根据商品查询查询商品列表
     *
     * @param param
     * @return
     * @throws LogicException
     */
    @GetMapping(value = "list")
    @SentinelResource(
            blockHandler = "handleInfoException"
    )
    public ResultData getList(@RequestParam Map<String, String> param) throws LogicException {
        String ids = (String) param.get("ids");

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (ids == null || "".equals(ids)) {
            return ResultData.error("请输入需要查询的商品ID");
        }
        String[] idsArr = ids.split(",");
        wrapper.in("id", Arrays.asList(idsArr));
        if (DateUtil.getTimestamp() % 2 == 0) {
            throw new LogicException("模拟异常发生！");
        }
        List<Goods> list = goodsService.list(wrapper);
        list.forEach(item -> {
            item.setGoodsImgPath(ToolUtil.getRealUrl(item.getGoodsImg()));
        });
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        return ResultData.success("success", map);
    }


    @PostMapping("updateStock")
    public ResultData updateStock(@RequestParam("goodsId") int goodsId, @RequestParam("num") int num) throws Exception {
        Goods entity = goodsMapper.selectById(goodsId);
        if (entity == null) {
            return ResultData.error(500, "商品不存在", null);
        }

        if (entity.getGoodsNumber() < num) {
            return ResultData.error(500, "商品库存不足，剩余" + entity.getGoodsNumber());
        }

        entity.setGoodsNumber(entity.getGoodsNumber() - num);
        entity.setUpdatedAt(DateUtil.getTimestamp());
        goodsMapper.updateById(entity);

        return ResultData.success();
    }

    @PostMapping("edit")
    @Transactional
    public ResultData edit(@RequestParam Map<String, Object> param) {

        int id = ParamUtil.getInt("goodsId", param, 0);
        Goods goods = null;
        if (id > 0) {
            goods = goodsService.getById(id);
        }
        param.put("goods", goods);
        param.put("id", id);

        //校验商品品牌、系列、分类信息
        ResultData resultData;
        resultData = goodsService.checkGoodsParam(param, id);
        if (resultData.getStatus() != 200) {
            return resultData;
        }

        //商品图片信息校验
        resultData = goodsService.checkGoodsAlbum(param, id);
        if (resultData.getStatus() != 200) {
            return resultData;
        }
        List<GoodsAlbum> goodsAlbumList = (List<GoodsAlbum>) resultData.getData();

        resultData = goodsService.checkMainDataGoodsCode(param);
        if (resultData.getStatus() != 200) {
            return resultData;
        }
        //保存商品信息
        goodsService.saveGoods(goods, param, id);

        //更新相册
        goodsService.saveGoodsAlbum(id, goodsAlbumList);

        //更新操作日志

        return ResultData.success();
    }

    public ResultData handleInfoException(int id, BlockException b) {
        System.out.println("商品详情接口，流量超过限制！{exception}");
        return ResultData.error(500, "商品详情接口，流量超过限制{exception}！");
    }
}
