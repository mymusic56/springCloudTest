package com.zsj.goods.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(value = {"com.zsj.goods"})
public class RestTemplateTest {
    @Test
    public void testGet(){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("-===================");
    }
}
