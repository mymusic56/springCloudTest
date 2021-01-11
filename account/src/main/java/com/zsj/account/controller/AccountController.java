package com.zsj.account.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zsj.account.entity.AccountAmountLogEntity;
import com.zsj.account.entity.AccountEntity;
import com.zsj.account.mybatis.mapper.AccountAmountLogMapper;
import com.zsj.account.mybatis.mapper.AccountMapper;
import com.zsj.account.service.AccountService;
import com.zsj.account.excpetion.sentinel.HandleAccountException;
import com.zsj.lib.utils.DateUtil;
import com.zsj.lib.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    AccountAmountLogMapper logMapper;

    @GetMapping("findById")
    @SentinelResource(
            blockHandlerClass = HandleAccountException.class,
            blockHandler = "handleInfoException"
    )
    public ResultData findById(@RequestParam("accountId") int accountId){
        if (DateUtil.getTimestamp() % 2 == 0) {
//            throw new RuntimeException("模拟异常发生！");
        }
        AccountEntity entity = accountMapper.findById(accountId);
        if (entity == null) {
            return ResultData.error(500, "用户不存在", null);
        }
        return ResultData.success("成功", entity);
    }

    @PostMapping("updateAmount")
    public ResultData updateAmount(@RequestParam("accountId") int accountId, @RequestParam("amount") double amount) {
        AccountEntity entity = accountMapper.findById(accountId);
        if (entity == null) {
            return ResultData.error(500, "用户不存在", null);
        }

        if (entity.getAmount() < amount) {
            return ResultData.error(500, "用户余额不足，剩余" + entity.getAmount(), null);
        }

        entity.setAmount(entity.getAmount() - amount);
        accountMapper.update(entity);

        AccountAmountLogEntity logEntity = new AccountAmountLogEntity();
        logEntity.setAccountId(accountId);
        logEntity.setChangeType(1);
        logEntity.setAmount(amount);
        logEntity.setCreatedAt(DateUtil.getTimestamp());
        logMapper.insert(logEntity);

        return ResultData.success("成功", null);
    }

    @GetMapping("list")
    public List<AccountEntity> listAccount(@RequestParam Map<String, String> param) {

        String accountName = param.getOrDefault("accountName", "");
        int page = Integer.valueOf(param.getOrDefault("page", "1"));
        int pageSize = Integer.valueOf(param.getOrDefault("pageSize", "10"));

        List<AccountEntity> list = accountService.listByAccountName(accountName, page, pageSize);
        log.info("输入名字：" + accountName);
        return list;
    }

    @PutMapping("update")
    public ResultData update(@RequestParam Map<String, Object> param) {
        int id = Integer.valueOf((String) param.get("id"));
        if (id == 0) {
            return ResultData.error(500, "id 不能为空", null);
        }

        AccountEntity entity = accountMapper.findById(id);
        if (entity == null) {
            return ResultData.error(500, "账户不存在！", null);
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(id);
        accountEntity.setAccountCode((String) param.get("accountCode"));
        accountEntity.setAccountName((String) param.get("accountName"));
        accountEntity.setAmount(Float.parseFloat((String) param.get("amount")));
        accountEntity.setUpdatedAt(DateUtil.getTimestamp());

        int updateCount = accountMapper.update(accountEntity);

        return ResultData.success("操作成功", updateCount);
    }

    @DeleteMapping("delete")
    public ResultData delete(@RequestParam String id) {
        int newid = Integer.parseInt(id);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(newid);
        accountEntity.setUpdatedAt(DateUtil.getTimestamp());
        int a = accountMapper.deleteById(accountEntity);

        return ResultData.success("", a);
    }

    @DeleteMapping("deleteV2")
    public ResultData deleteV2(@RequestParam String id) {
        int newid = Integer.parseInt(id);
        int a = accountMapper.deleteByIdV2(newid, DateUtil.getTimestamp());

        return ResultData.success("", a);
    }

    @DeleteMapping("deleteV3")
    public ResultData deleteV3(@RequestParam String id) {
        int newid = Integer.parseInt(id);
        int a = accountMapper.deleteByIdV3(newid, DateUtil.getTimestamp());

        return ResultData.success("", a);
    }

    @DeleteMapping("batchDelete")
    public ResultData batchDelete(@RequestParam String ids) {
        if (ids.isEmpty()) {
            return ResultData.error(500, "缺少ID", null);
        }
        String[] ids1 = ids.split(",");

        int[] ids2 = new int[ids1.length];
        int j = 0;
        for (String i : ids1) {
            int a = Integer.valueOf(i);
            if (a > 0) {
                ids2[j] = a;
                j ++;
            }
        }

        if (ids2.length < ids1.length) {
            return ResultData.error(500, "存在无效的数据", null);
        }

        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids2);
        param.put("updatedAt", DateUtil.getTimestamp());
        int a = accountMapper.deleteByIds(param);

        return ResultData.success("", a);
    }

    @GetMapping("{name}")
    public AccountEntity findByName(@PathVariable String name) {
        return accountService.findByAccountName(name);
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello" + name;
    }

    @PostMapping("/hello")
    public String hello2(@RequestParam("accountName") String name) {
        return "hello" + name;
    }

    @GetMapping("/hello")
    public String hello3(@RequestParam Map<String, Object> param) {
        return "hello" + param.getOrDefault("accountName", " 无名氏 ");
    }

}
