package com.lqj.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 李岐鉴
 * @Date 2025/12/13
 * @Description http 框架
 */
@Configuration
public class OKHttpClientConfig {

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

}
