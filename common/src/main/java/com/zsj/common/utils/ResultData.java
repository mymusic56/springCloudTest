package com.zsj.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class ResultData<T> {
    private int status;
    private String message;
    private T data;

    public ResultData(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
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
