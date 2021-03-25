package com.niu.spring.service;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niu.spring.client.ClientBRestTemplateClient;
import com.niu.spring.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 客户端B Service
 *
 * @author [nza]
 * @version 1.0 [2021/03/24 16:11]
 * @createTime [2021/03/24 16:11]
 */
@Service
@AllArgsConstructor
@Slf4j
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
        }
)
public class ClientBService {

    private final ClientBRestTemplateClient clientBRestTemplateClient;

    /**
     * 查询客户端B信息
     * <p>
     * 断路器
     * 设置超时时间为 5s
     *
     * @return {@link Map<String, Object>}
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    public Map<String, Object> findClientbInfoByTimeOut() {

        log.info("断路器，当前IP: {}", UserContextHolder.getContext().getCurrentIp());

        // 睡眠 6s
        sleep6s();

        Map<String, Object> res = Maps.newHashMap();

        Object data = clientBRestTemplateClient.findClientB();
        res.put("status", "1");
        res.put("data", data);
        return res;
    }

    /**
     * 查询客户端B信息
     * <p>
     * 后备
     * fallbackMethod 属性配置后备方法
     * 调用失败后会执行后备方法
     *
     * @return {@link Map<String, Object>}
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Map<String, Object> findClientbInfoByBack() {

        log.info("后备, 当前IP: {}", UserContextHolder.getContext().getCurrentIp());

        // 睡眠 6s
        sleep6s();

        Map<String, Object> res = Maps.newHashMap();

        Object data = clientBRestTemplateClient.findClientB();
        res.put("status", "1");
        res.put("data", data);
        return res;
    }

    /**
     * 查询客户端B信息
     * <p>
     * 舱壁
     * 独立线程池调用
     * threadPoolKey 属性定义线程池的唯一名称
     * threadPoolProperties 属性用于定义和定制threadPool的行为
     * coreSize 属性用于定义线程池中线程的最大数量
     * maxQueueSize 用于定义一个位于线程池前的队列，它可以对传入的请求进行排队
     *
     * @return {@link Map<String, Object>}
     */
    @HystrixCommand(
            threadPoolKey = "myBulk",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public Map<String, Object> findClientbInfoByBulk() {

        log.info("舱壁, 当前IP: {}", UserContextHolder.getContext().getCurrentIp());

        Map<String, Object> res = Maps.newHashMap();

        Object data = clientBRestTemplateClient.findClientB();
        res.put("status", "1");
        res.put("data", data);
        return res;
    }

    /**
     * 后备方法
     *
     * @return {@link java.util.Map<java.lang.String,java.lang.Object>}
     * @author nza
     * @createTime 2021/3/24 16:44
     */
    public Map<String, Object> fallback() {
        Map<String, Object> res = Maps.newHashMap();
        res.put("status", "3");
        res.put("data", "后备方法");
        return res;
    }

    private void sleep6s() {
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            log.debug("error: ", e);
        }
    }
}
