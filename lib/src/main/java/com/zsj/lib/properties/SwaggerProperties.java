package com.zsj.lib.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private boolean enabled;
    private String version;
    private String title;
    private String description;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
}
