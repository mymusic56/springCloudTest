package com.zsj.lib.handler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom-config")
@Data
public class CustomConfigHandler {
    private String uploadsDir;

    private String imgHost;

    //主数据相关配置
    private String mainDataClientId;
    private String mainDataSecret;
    private Integer mainDataExpires;
    private String mainDataBaseUrl;
}
