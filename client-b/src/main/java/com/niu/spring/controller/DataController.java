package com.niu.spring.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/23 14:49]
 * @createTime [2021/03/23 14:49]
 */
@RestController
@RequestMapping()
public class DataController {

    @GetMapping
    public Map<String, String> findInfo() {
        Map<String, String> res = Maps.newHashMap();
        res.put("id", "clientB");
        res.put("status", "up");
        return res;
    }
}
