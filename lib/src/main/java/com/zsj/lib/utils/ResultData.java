package com.zsj.lib.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ApiModel
public class ResultData<T> {
    @ApiModelProperty(value = "状态码")
    private int status;
    @ApiModelProperty(value = "返回信息")
    private String message;
    private String error;
    @ApiModelProperty(value = "数据集合")
    private T data;

    protected ResultData(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResultData success() {
        return new ResultData<>(200, "操作成功", new HashMap<>());
    }

    public static ResultData success(String message) {
        return new ResultData<>(200, message, new HashMap<>());
    }

    public static<T> ResultData<T> success(String message, T data) {
        return new ResultData<>(200, message, data);
    }

    public static ResultData error(String message) {
        return new ResultData<>(500, message, new HashMap<>());
    }

    public static ResultData error(Integer status, String message) {
        return new ResultData<>(500, message, new HashMap<>());
    }

    public static<T> ResultData<T> error(String message, T data) {
        return new ResultData<>(500, message, data);
    }

    public static<T> ResultData<T> error(Integer status, String message, T data) {
        return new ResultData<>(status, message, data);
    }

    public String toJson (){
        T data = this.data;
        if (data instanceof List || data instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }
}
