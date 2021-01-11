package com.zsj.lib.enums;

public enum StatusRemark {

    ENABLED(1, "禁用"),
    DISABLED(0, "启用");

    private int status;
    private String remark;

    public int getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }


    StatusRemark(int status, String remark) {
        this.status = status;
        this.remark = remark;
    }

}
