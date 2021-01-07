package com.zsj.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@MapperScan("com.zsj.goods.mapper")
@ComponentScan(value = {"com.zsj.common", "com.zsj.goods"})
public class GoodsServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApp.class, args);
    }
}
