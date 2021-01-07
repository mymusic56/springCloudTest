package com.zsj.goods.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.zsj.goods.mapper")
@ComponentScan(value = {"com.zsj.common", "com.zsj.goods"})
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        stringRedisTemplate.opsForValue().set("test_name", "zhangsan", 120, TimeUnit.SECONDS);
        System.out.println("===Redis 设置值===");
    }

    @Test
    public void testGet(){
        String val = stringRedisTemplate.opsForValue().get("test_name");
        System.out.println("===Redis val ===" + val);
    }

    @Test
    public void testGetExpired(){
        long val = stringRedisTemplate.getExpire("test_name");
        System.out.println("===Redis Expire val ===" + val);
    }

}
