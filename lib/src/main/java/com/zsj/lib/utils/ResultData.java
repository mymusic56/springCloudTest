package com.zsj.lib.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ResultData<T> {
    private int status;
    private String message;
    private String error;
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
