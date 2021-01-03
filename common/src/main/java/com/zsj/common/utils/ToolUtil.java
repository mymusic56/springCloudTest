package com.zsj.common.utils;

import org.springframework.context.annotation.PropertySource;

public class ToolUtil {
    public static String getRealUrl(String host, String img) {
        if (img.startsWith("http://") || img.startsWith("https://")) {
            return img;
        }
        if (img.startsWith("/")) {
            return host + img;
        } else {
            return host + "/" + img;
        }
    }
}
