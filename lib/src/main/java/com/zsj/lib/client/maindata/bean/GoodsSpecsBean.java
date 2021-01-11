package com.zsj.lib.client.maindata.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GoodsSpecsBean {

    private String specsId;
    private GoodsTypeBean goodsType;
    private GoodsSeriesBean goodsSeries;
    private GoodsBrandBean goodsBrand;
    private String goodsCode;
    private String gsname;
    private int status;
    private int verifyState;
    private String unitCode;
    private String sunitCode;
    private String punitCode;
    private double netWeight;
    private double grossWeight;
    private String barcode;
    private String gsspecs;
    private String lanCode;
    private int batchFlag;
    private int shelfLife;
    private String goodsSubject;
    private String projectType;
    private String outputTax;
    private Object specsRemark;
    private String createUser;
    private String createTime;
    private Object updateUser;
    private Object updateTime;
    private Object thCode;
    private Object abroadEnt;
    private Object tare;

    @NoArgsConstructor
    @Data
    public static class GoodsTypeBean {
        private String goodsTypeId;
        private String gtcode;
        private String gtname;
        private String pid;
        private String pids;
        private String pnames;
        private int gtlevel;
        private int status;
        private String remark;
        private String createUser;
        private Object createTime;
        private String updateUser;
        private Object updateTime;
    }

    @NoArgsConstructor
    @Data
    public static class GoodsSeriesBean {
        private String seriesId;
        private String brandId;
        private String gscode;
        private String gsname;
        private Object remark;
        private int status;
        private String createUser;
        private String createTime;
        private Object updateUser;
        private Object updateTime;
        private int isDeleted;
    }

    @NoArgsConstructor
    @Data
    public static class GoodsBrandBean {
        private String brandId;
        private String gbcode;
        private String gbname;
        private int status;
        private String country;
        private String logo;
        private String company;
        private Object remark;
        private String createUser;
        private String createTime;
        private String updateUser;
        private String updateTime;
    }
}
