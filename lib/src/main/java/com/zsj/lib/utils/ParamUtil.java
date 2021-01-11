package com.zsj.lib.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParamUtil {

    public static int getInt(String filed, Map<String, Object> param, int defaultValue) {
        return Integer.parseInt(param.getOrDefault(filed, defaultValue).toString());
    }

    public static int getInt(String filed, Map<String, Object> param) {
        return Integer.parseInt(param.get(filed).toString());
    }

    public static String getString(String filed, Map<String, Object> param) {
        return param.get(filed).toString();
    }

    public static String getString(String filed, Map<String, Object> param, String defaultValue) {
        return param.getOrDefault(filed, defaultValue).toString();
    }

    public static Map<String, Object> checkUpdatedFiled(Object entity, Map<String, Object> param) {
        Map<String, Object> update = new HashMap<>();
        if (entity == null) {
            return update;
        }
        Set<String> paramName = param.keySet();
        Field[] fields = entity.getClass().getDeclaredFields();
        Object fvalue;
        for (Field f : fields) {
            String fname = f.getName();
            if (paramName.contains(fname)) {
                //判断值是否相等
                try {
                    f.setAccessible(true);
                    fvalue = f.get(entity);
                    Object val = param.get(fname);
                    if (val != null) {
                        //处理实例中是Integer类型的字段
                        if (f.getType() == Integer.class) {
                            if (Integer.parseInt((String) val) != (Integer) fvalue) {
                                update.put(fname, fvalue + " -> " + val);
                                f.setAccessible(false);
                            }
                        } else {
                            if (!val.equals(fvalue)) {
                                update.put(fname, fvalue + " -> " + val);
                            }
                        }
                        f.setAccessible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return update;
    }
}
