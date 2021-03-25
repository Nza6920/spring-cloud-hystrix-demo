package com.niu.spring.config;

import com.niu.spring.intercepter.CustomInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/23 14:46]
 * @createTime [2021/03/23 14:46]
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 带有 Ribbon 功能的 RestTemplate
     *
     * @return {@link RestTemplate}
     * @author nza
     * @createTime 2021/3/3 23:18
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/clients/**");
    }
}
