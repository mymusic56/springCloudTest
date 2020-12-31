package com.zsj.account.mybatis.mapper;

import com.zsj.account.entity.AccountAmountLogEntity;
import org.apache.ibatis.annotations.Insert;

public interface AccountAmountLogMapper {

    @Insert("INSERT INTO account_amount_log (change_type,account_id,amount,created_at)" +
            " VALUES (#{changeType},#{accountId},#{amount},#{createdAt})")
    public int insert(AccountAmountLogEntity amountLogEntity);
}
