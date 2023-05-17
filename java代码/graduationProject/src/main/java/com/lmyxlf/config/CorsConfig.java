package com.lmyxlf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * 解决前后端跨域问题
 *
 * @author lmy
 */
@Configuration
public class CorsConfig {
    /**
     * 配置处理前后端跨域的对象
     *
     * @return 处理前后端跨域的对象
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有域名跨域请求
        corsConfiguration.addAllowedOrigin("*");
        // 允许所有请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方法
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}