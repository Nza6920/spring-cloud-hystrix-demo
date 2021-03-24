package com.niu.spring.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 启用了 RestTemplate 的 Spring DiscoveryClient
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 21:33]
 * @createTime [2021/03/03 21:33]
 */
@Component
@AllArgsConstructor
@Slf4j
public class ClientBRestTemplateClient {

    private final RestTemplate restTemplate;

    /**
     * 查询 ClientB
     *
     * @return {@link Object} 机构
     * @author nza
     * @createTime 2021/3/4 15:57
     */
    public Object findClientB() {

        // 使用服务名称传递
        String uri = "http://clientb/api/clientb";

        // 使用网关传递
        ResponseEntity<Map> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, Map.class);

        return exchange.getBody();
    }
}
