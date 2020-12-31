package com.zsj.account.service;

import com.zsj.account.entity.AccountEntity;
import com.zsj.account.mybatis.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Resource
    private AccountMapper accountMapper;

    public List<AccountEntity> listByAccountName(String accountName, int page, int pageSize) {

        int startIndex = (page - 1) * pageSize;


        Map<String, Object> map = new HashMap<>();
        map.put("account_name", accountName);
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        return accountMapper.findByPage(map);
    }

    public AccountEntity findByAccountName(String name) {
        return accountMapper.findByAccountName(name);
    }
}
