package com.niu.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/23 14:33]
 * @createTime [2021/03/23 14:33]
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class, args);
    }
}
