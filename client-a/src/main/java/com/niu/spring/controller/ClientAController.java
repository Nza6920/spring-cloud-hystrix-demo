package com.niu.spring.controller;

import com.niu.spring.service.ClientBService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 客户端控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/23 14:53]
 * @createTime [2021/03/23 14:53]
 */
@RestController
@RequestMapping("clients")
@AllArgsConstructor
@Slf4j
public class ClientAController {

    private final ClientBService clientBService;

    /**
     * 获取ClientB信息
     * 断路器
     *
     * @author nza
     * @createTime 2021/3/23 15:35
     */
    @GetMapping("clientb/timeout")
    public Map<String, Object> findClientbInfoByTimeOut() {
        log.info("断路器控制器");
        return clientBService.findClientbInfoByTimeOut();
    }

    /**
     * 获取 ClientB 信息
     * 后备
     *
     * @author nza
     * @createTime 2021/3/23 15:35
     */
    @GetMapping("clientb/back")
    public Map<String, Object> findClientbInfoByBack() {
        log.info("后备控制器");
        return clientBService.findClientbInfoByBack();
    }

    /**
     * 获取 ClientB 信息
     * 舱壁
     *
     * @author nza
     * @createTime 2021/3/23 15:35
     */
    @GetMapping("clientb/bulk")
    public Map<String, Object> findClientbInfoByBulk() {
        log.info("舱壁控制器");
        return clientBService.findClientbInfoByBulk();
    }
}
