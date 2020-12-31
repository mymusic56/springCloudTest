package com.zsj.common.utils;

public class ResultUtil {

    public static String json(int status, String message, Object data) {
        return new ResultData<Object>(status, message, data).toJson();
    }

}
