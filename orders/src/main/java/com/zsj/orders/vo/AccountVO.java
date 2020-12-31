package com.zsj.orders.vo;

import com.zsj.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountVO {
    private int id;
    private String accountCode;
    private String accountName;
    private double amount;
    private int updatedAt;
}
