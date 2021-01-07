package com.zsj.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public static Map<String, Object> checkUpdatedFiled(Object entity, Map<String, Object> param){
        Set<String> paramName = param.keySet();
        Field[] fields = entity.getClass().getFields();
        Object fvalue;
        Map<String, Object> update = new HashMap<>();
        for (Field f : fields) {
            String fname = f.getName();
            if (paramName.contains(fname)) {
                //判断值是否相等
                try {
                    fvalue = f.get(entity);
                    if (!param.get(fname).equals(fvalue)) {
                        update.put(fname, fvalue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return update;
    }
}