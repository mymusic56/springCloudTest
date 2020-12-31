package com.zsj.orders;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zsj.orders.mybatis.mapper")
public class OrdersServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OrdersServiceApp.class, args);
    }
}
