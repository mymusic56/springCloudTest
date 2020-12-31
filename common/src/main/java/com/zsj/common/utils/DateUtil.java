package com.zsj.common.utils;

import java.util.Date;

public class DateUtil {
    public static int getTimestamp(){
        return Integer.valueOf(String.valueOf(new Date().getTime()/1000));
    }

    public static String getDatetime(int time){
        return new Date(time).toString();
    }
}
