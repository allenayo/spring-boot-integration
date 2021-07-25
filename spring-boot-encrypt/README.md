# SpringBoot加密外部文件属性
## 引入关键配置
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
    implementation 'com.github.ulisesbocchio:jasypt-spring-boot-starter:2.1.2'
}
```
## 外部文件配置
```
global.projectName=spring-boot-encrypt
-- ENC(加密字段)
global.author=ENC(QH0DgNSGmV4y62rlIiKDY3XO31L8sCGA)
```
## 引入外部文件
```java
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
```
## 加密秘钥配置
1. 配置文件中配置
 ```
 # jasypt配置
 jasypt.encryptor.password=qwe!@3as
 ```
2. 命令行中配置

3. 环境变量中配置


