package com.allenayo.sbe.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Setter
@Getter
@Configuration
@PropertySource("classpath:global.properties")
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {
    private String projectName;
    private String author;
}