package com.zsj.orders.service.fallback;

import com.zsj.orders.service.AccountService;

public class AccountServiceFallback implements AccountService {

    private Throwable throwable;

    AccountServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String findByAccountName(String accountName) {
        return "findByAccountName" + throwable.getMessage();
    }

    @Override
    public String findById(int accountId) {
        return "{\"status\":501,\"message\":\"findById熔断，" + throwable.getMessage() +"\"}";
    }

    @Override
    public String updateAmount(int accountId, double amount) {
        return "{\"status\":501,\"message\":\"updateAmount熔断" + throwable.getMessage() +"\"}";
    }
}
