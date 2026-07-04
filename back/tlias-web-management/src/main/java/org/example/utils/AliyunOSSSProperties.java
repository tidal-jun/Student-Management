package org.example.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss") //配置环境配置导入值
public class AliyunOSSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
