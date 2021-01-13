package com.zsj.account.entity;

import com.zsj.lib.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ApiModel
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户ID")
    private int id;

    @ApiModelProperty(value = "账户编码")
    private String accountCode;
    @ApiModelProperty(value = "账户名称")
    private String accountName;
    @ApiModelProperty(value = "账户余额")
    private double amount;
    @ApiModelProperty(value = "更新时间")
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
