package com.spike.codegenerationservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class GlobalConfig {
    private String packageName;
    private String tables;
    private String jdbcUrl;
    private String jdbcPassword;

    @Override
    public String toString() {
        return "GlobalConfig{" +
                "packageName='" + packageName + '\'' +
                ", tables='" + tables + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                '}';
    }
}
