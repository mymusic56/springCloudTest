package com.zsj.orders.service.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceFallbackFactory implements FallbackFactory<AccountServiceFallback> {
    @Override
    public AccountServiceFallback create(Throwable throwable) {
        return new AccountServiceFallback(throwable);
    }
}
