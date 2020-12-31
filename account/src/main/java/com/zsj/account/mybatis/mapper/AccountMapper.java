package com.zsj.account.mybatis.mapper;

import com.zsj.account.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AccountMapper {
    public List<AccountEntity> findByPage(Map<String, Object> params);

    public AccountEntity findByAccountName(String name);

    public AccountEntity findById(int id);

    public int update(AccountEntity accountEntity);

    public int increaseAmount(AccountEntity accountEntity);

    //两种方式都更新失败
    public int deleteById(AccountEntity accountEntity);

    public int deleteByIdV2(int id, int updatedAt);

    public int deleteByIdV3(@Param("accountId") int id, @Param("updated_at") int updatedAt);

    public int deleteByIds(Map<String, Object> param);
}
