package com.person.system.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * restTeamplate配置
 * @author sk
 * @date 2024/4/24
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5)) // 设置连接超时时间为5秒
                .setReadTimeout(Duration.ofSeconds(10)) // 设置读取超时时间为10秒
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8)) // 添加字符编码
                .build();
    }
}
