package com.niu.spring.exception;

import com.google.common.collect.Maps;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 统一异常处理
 *
 * @author [nza]
 * @version 1.0 [2021/03/24 16:23]
 * @createTime [2021/03/24 16:23]
 */
@ControllerAdvice(basePackages = {"com.niu.spring.controller.*"}, annotations = {RestController.class})
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> hystrixRuntimeExceptionHandler(HystrixRuntimeException ex) {

        log.error("统一异常拦截: ", ex);

        Map<String, Object> res = Maps.newHashMap();
        res.put("status", "0");
        res.put("msg", ex.getMessage());
        return res;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> exceptionHandler(Exception ex) {

        log.error("统一异常拦截: ", ex);

        Map<String, Object> res = Maps.newHashMap();
        res.put("status", "0");
        res.put("msg", ex.getMessage());
        return res;
    }
}
