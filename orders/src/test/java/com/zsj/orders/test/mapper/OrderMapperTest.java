package com.zsj.orders.test.mapper;

import com.alibaba.fastjson.JSONObject;
import com.zsj.orders.OrdersServiceApp;
import com.zsj.orders.entity.OrdersEntity;
import com.zsj.orders.mybatis.mapper.OrdersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangshangji
 * @since 2021/2/3 14:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrdersServiceApp.class)
public class OrderMapperTest {

    @Resource
    OrdersMapper ordersMapper;

    @Test
    public void leftJoinQuery() {
        String[] olist = {"S123456", "S123457"};
        List<OrdersEntity> list = ordersMapper.listOrderWithGoodsByOrderSn(Arrays.asList(olist));
        System.out.println("JSONObject.toJSON(list) = " + JSONObject.toJSON(list));
    }
}
