package com.zsj.account.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountAmountLogEntity {
    private long id;
    private int changeType;
    private int accountId;
    private double amount;
    private int createdAt;
}
