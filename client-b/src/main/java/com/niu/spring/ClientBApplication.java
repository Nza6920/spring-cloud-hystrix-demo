package com.niu.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/23 14:32]
 * @createTime [2021/03/23 14:32]
 */
@SpringBootApplication
@EnableEurekaClient
public class ClientBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientBApplication.class, args);
    }
}
