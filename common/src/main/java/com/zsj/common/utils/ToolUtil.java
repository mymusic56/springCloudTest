package com.zsj.common.utils;

import com.zsj.common.handler.CustomConfigHandler;
import com.zsj.common.handler.SpringContextHandler;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ToolUtil {
    public static String getRealUrl(String img) {
        if (img.startsWith("http://") || img.startsWith("https://")) {
            return img;
        }

        String host = SpringContextHandler.getBean(CustomConfigHandler.class).getImgHost();

        if (img.startsWith("/")) {
            return host + img;
        } else {
            return host + "/" + img;
        }
    }

    public static boolean checkFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static String httpBuildQuery(Map<String, String> param){
        String key;
        String value;
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, String> entry: param.entrySet()){
            key = entry.getKey();
            value = entry.getValue();
            try {
                stringBuilder.append(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

}
