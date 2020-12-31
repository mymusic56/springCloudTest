package com.zsj.orders.service;

import com.zsj.orders.service.fallback.AccountServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service", fallbackFactory = AccountServiceFallbackFactory.class)
public interface AccountService {

    @GetMapping("/account/{accountName}")
    String findByAccountName(@PathVariable("accountName") String accountName);

    @GetMapping("/account/findById")
    String findById(@RequestParam("accountId") int accountId);

    @PostMapping("/account/updateAmount")
    String updateAmount(@RequestParam("accountId") int accountId, @RequestParam("amount") double amount);
}
