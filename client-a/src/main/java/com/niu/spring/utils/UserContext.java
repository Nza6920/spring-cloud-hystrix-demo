package com.niu.spring.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户上下文
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 12:11]
 * @createTime [2021/03/07 12:11]
 */
@Data
@Accessors(chain = true)
public class UserContext {

    /**
     * 当前ID
     */
    private String currentIp = "";
}
