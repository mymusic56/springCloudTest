package com.zsj.common.utils;

import com.zsj.common.consts.DateFormatConst;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static int getTimestamp(){
        return Integer.valueOf(String.valueOf(new Date().getTime()/1000));
    }

    /**
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDatetime(){
        return new SimpleDateFormat(DateFormatConst.YYYY_MM_DD_HH_MM_SS_EN).format(Calendar.getInstance().getTime());
    }

    /**
     *
     * @param format
     * @return
     */
    public static String getDatetime(String format){
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
    /**
     *
     * @param time
     * @return
     */
    public static String getDatetime(int time){
        return new Date(time).toString();
    }
    /**
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDatetime(int time, String format){
        return new SimpleDateFormat(format).format(new Date((long) time*1000));
    }
}
