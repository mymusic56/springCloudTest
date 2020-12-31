package com.zsj.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.zsj.account.mybatis.mapper")
@EnableDiscoveryClient
public class AccountServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApp.class, args);
    }
}
