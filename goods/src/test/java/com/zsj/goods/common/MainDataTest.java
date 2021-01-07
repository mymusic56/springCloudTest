package com.zsj.goods.common;

import com.alibaba.fastjson.JSONObject;
import com.zsj.common.client.maindata.MaindDataApiImpl;
import com.zsj.common.client.maindata.bean.GoodsSpecsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.zsj.goods.mapper")
@ComponentScan(value = {"com.zsj.common", "com.zsj.goods"})
public class MainDataTest {

    @Autowired
    MaindDataApiImpl maindDataApi;

    @Test
    public void testGetGoodsSepc() {
        GoodsSpecsBean goodsSpecsBean = maindDataApi.getGoodsSpecsDetail("202577");
        System.out.println("JSONObject.toJSONString(goodsSpecsBean) = " + JSONObject.toJSONString(goodsSpecsBean));
    }
}
