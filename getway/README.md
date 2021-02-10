### 网关动态路由配置示例
```yaml
server:
  port: 8009
spring:
  cloud:
    nacos:
      discovery:
        server-addr: home.mytest.com:8848
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
      - id: goods-service
        uri: lb://goods-service
        predicates:
        - Path=/goods/**
      - id: account-service
        uri: lb://account-service
        predicates:
        - Path=/account/**
      - id: order-service
        uri: lb://orders-service
        predicates:
        - Path=/orders/**
```