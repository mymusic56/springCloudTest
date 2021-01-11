package com.zsj.orders.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsj.orders.entity.AccountEntity;
import com.zsj.orders.entity.GoodsEntity;
import com.zsj.orders.exception.LogicException;
import com.zsj.orders.service.GoodsService;
import com.zsj.lib.utils.DateUtil;
import com.zsj.lib.utils.ResultData;
import com.zsj.orders.service.AccountService;
import com.zsj.orders.dto.OrderDTO;
import com.zsj.orders.dto.OrderGoodsDTO;
import com.zsj.orders.mybatis.mapper.OrdersGoodsMapper;
import com.zsj.orders.mybatis.mapper.OrdersMapper;
import com.zsj.orders.vo.AccountVO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private AccountService accountService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrdersGoodsMapper ordersGoodsMapper;

    @GetMapping("info/{orderId}")
    public ResultData info(@PathVariable("orderId") int orderId) {
        Map<String, Object> map = new HashMap<>();

        //調用account-service
        String accountInfo = accountService.findById(1);
        map.put("orderId", orderId);

        if (accountInfo.isEmpty()) {
            map.put("accountInfoString", "账户信息为空！");
        } else {
            map.put("accountInfoString", accountInfo);

            Map<String, Object> accountRes = JSONObject.parseObject(accountInfo);
            if ((int) accountRes.get("status") == 200) {
                AccountEntity entity = JSONObject.parseObject(accountInfo, AccountEntity.class);
                map.put("accountInfo", entity);
            }
        }


        String goodsInfo = goodsService.info(1);

        if (goodsInfo.isEmpty()) {
            map.put("goodsInfo", "商品信息未空！");
        } else {
            map.put("goodsInfoString", goodsInfo);

            Map<String, Object> goodsInfoRes = JSONObject.parseObject(goodsInfo);
            if ((int) goodsInfoRes.get("status") == 200) {
                GoodsEntity entity = JSONObject.parseObject(goodsInfo, GoodsEntity.class);
                map.put("goodsInfo", entity);
            }
        }


        return ResultData.success("success", map);
    }

    @GetMapping("goodsList")
    public ResultData goodsList(String ids) {
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        ResultData res = goodsService.goodsList(param);
        if (res.getStatus() != 200) {
            return ResultData.error("商品中心接口调用失败（" + res.getMessage() + ")");
        }
        return ResultData.success("", res.getData());
    }

    @PostMapping("create")
    @GlobalTransactional(timeoutMills = 30000, name = "spring-cloud-demo-tx")
    public ResultData create(@RequestParam Map<String, Object> param) throws Exception {

        int accountId = 1;

        String res = accountService.findById(accountId);

        JSONObject jsonObject = JSONObject.parseObject(res);
        if ((int) jsonObject.get("status") != 200 || jsonObject.get("data") == null) {
            return ResultData.error(500, "账户信息获取失败，" + jsonObject.get("message"), jsonObject.get("data"));
        }
        AccountVO accountVO = jsonObject.getObject("data", AccountVO.class);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId((int) accountVO.getId());
        orderDTO.setAddress("收货地址");
        orderDTO.setAmount(25.36);
        orderDTO.setCreatedAt(DateUtil.getTimestamp());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = "O" + sdf.format(new Date());
        int max = 999, min = 100;
        String orderSn = str + (int) (Math.random() * (max - min) + min);

        orderDTO.setOrderSn(orderSn);


        OrderGoodsDTO goodsDTO = new OrderGoodsDTO();
        goodsDTO.setGoodsId(Integer.parseInt((String) param.get("goodsId")));
        goodsDTO.setGoodsName("测试商品");
        goodsDTO.setGoodsSn("G12345");
        goodsDTO.setNum(2);

        //保存订单数据
        ordersMapper.insert(orderDTO);

        int orderId = orderDTO.getId();
        if (orderId == 0) {
            throw new LogicException("订单保存失败");
        }

        goodsDTO.setOrderId(orderId);

        ordersGoodsMapper.insert(goodsDTO);
        if (goodsDTO.getGoodsId() == 0) {
            throw new LogicException("订单商品保存失败");
        }

        //扣减金额
        String accountResult = accountService.updateAmount(accountId, orderDTO.getAmount());
        JSONObject accountResultJsonObject = JSONObject.parseObject(accountResult);
        if ((int) accountResultJsonObject.get("status") != 200) {
            throw new LogicException("账户金额扣减失败" + accountResultJsonObject.get("message"));
        }
        //扣减库存
        String goodsResult = goodsService.updateStock(goodsDTO.getGoodsId(), goodsDTO.getNum());
        JSONObject goodsResultJsonOjbect = JSONObject.parseObject(goodsResult);
        if ((int) goodsResultJsonOjbect.get("status") != 200) {
            throw new LogicException("库存扣减失败" + goodsResultJsonOjbect.get("message"));
        }
        return ResultData.success("创建成功", orderDTO);
    }

}
