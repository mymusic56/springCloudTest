package com.zsj.goods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.goods.feign.ActionLogFeign;
import com.zsj.lib.handler.SpringContextHandler;
import com.zsj.lib.utils.DateUtil;
import com.zsj.lib.utils.ParamUtil;
import com.zsj.lib.utils.ResultData;
import com.zsj.lib.utils.ToolUtil;
import com.zsj.goods.entity.Goods;
import com.zsj.goods.entity.GoodsAlbum;
import com.zsj.goods.exception.LogicException;
import com.zsj.goods.mapper.GoodsMapper;
import com.zsj.goods.service.IGoodsService;
import com.zsj.goods.vo.GoodsVo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
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

    @Autowired
    private ActionLogFeign actionLogFeign;

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
    @GlobalTransactional(timeoutMills = 30000, name = "spring-cloud-goods-edit-tx")
    public ResultData edit(@RequestParam Map<String, Object> param) throws LogicException {
        int id = ParamUtil.getInt("goodsId", param, 0);
        Goods goods = null;
        String actionName = "新增";
        if (id > 0) {
            goods = goodsService.getById(id);
            actionName = "编辑";
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
        //检测更新字段
        Map<String, Object> update = ParamUtil.checkUpdatedFiled(goods, param);
        log.info("商品表更新字段：" + JSONObject.toJSONString(update));
        goodsService.saveGoods(goods, param, id);

        //更新相册
        goodsService.saveGoodsAlbum(id, goodsAlbumList);

        //更新操作日志
        Map<String, Object> log = new HashMap<>();
        log.put("accountId", 1);
        log.put("actionId", 1);
        log.put("moduleId", 1);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.put("uri", request.getRequestURI());
        log.put("username", "root");
        log.put("resourceSn", id);
        log.put("ip", request.getRemoteAddr());
        log.put("content", update.toString());
        log.put("actionName", actionName);
        ResultData logRes = actionLogFeign.save(log);
        if (logRes.getStatus() != 200) {
            throw new LogicException("日志保存失败：" + logRes.getMessage());
        }
        return ResultData.success();
    }

    public ResultData handleInfoException(int id, BlockException b) {
        System.out.println("商品详情接口，流量超过限制！{exception}");
        return ResultData.error(500, "商品详情接口，流量超过限制{exception}！");
    }
}
