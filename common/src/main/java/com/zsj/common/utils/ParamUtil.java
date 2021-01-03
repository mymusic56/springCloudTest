package com.zsj.common.utils;

import java.util.Map;

public class ParamUtil {

    public static int getInt(String filed, Map<String, Object> param, int defaultValue){
        return Integer.parseInt(param.getOrDefault(filed, defaultValue).toString());
    }

    public static int getInt(String filed, Map<String, Object> param){
        return Integer.parseInt(param.get(filed).toString());
    }

    public static String getString(String filed, Map<String, Object> param){
        return param.get(filed).toString();
    }

    public static String getString(String filed, Map<String, Object> param, String defaultValue){
        return param.getOrDefault(filed, defaultValue).toString();
    }
}
