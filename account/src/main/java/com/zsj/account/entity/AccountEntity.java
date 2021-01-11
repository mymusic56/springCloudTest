package com.zsj.account.entity;

import com.zsj.lib.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private String accountCode;
    private String accountName;
    private double amount;
    private int updatedAt;


    public AccountEntity() {

    }

    public AccountEntity(String accountCode, String accountName, float amount) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.amount = amount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("id=").append(id);
        sb.append(",accountCode=").append(accountCode);
        sb.append(",accountName=").append(accountName);
        sb.append(",updatedAt=").append(DateUtil.getDatetime(updatedAt));
        sb.append("]");
        return sb.toString();
    }


}
