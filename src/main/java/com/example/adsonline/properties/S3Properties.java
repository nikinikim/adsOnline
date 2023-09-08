package com.example.adsonline.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "minio")
public class S3Properties {
    private String url;
    private String port;
    private String accessKey;
    private String secretKey;
}
