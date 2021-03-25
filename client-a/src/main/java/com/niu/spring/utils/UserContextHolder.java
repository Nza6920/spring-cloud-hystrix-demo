package com.niu.spring.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户上下文管理器
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 12:13]
 * @createTime [2021/03/07 12:13]
 */
@Slf4j
public class UserContextHolder {

    private static final ThreadLocal<UserContext> USER_CONTEXT = new ThreadLocal<>();

    /**
     * 获取上下文
     *
     * @return {@link UserContext}
     * @author nza
     * @createTime 2021/3/7 12:21
     */
    public static UserContext getContext() {

        UserContext context = USER_CONTEXT.get();

        if (context == null) {
            USER_CONTEXT.remove();
            context = createEmptyContext();
            setUserContext(context);
        }

        return USER_CONTEXT.get();
    }

    /**
     * 设置上下文
     *
     * @param context 上下文
     * @throws {@link IllegalArgumentException} 参数非法时抛出
     * @author nza
     * @createTime 2021/3/7 12:18
     */
    public static void setUserContext(UserContext context) {
        if (context == null) {
            throw new IllegalArgumentException("非法的方法参数");
        }
        log.info("设置线程本地变量, currentIp: {}", context.getCurrentIp());
        USER_CONTEXT.set(context);
    }

    /**
     * 创建空的上下文对象
     *
     * @return {@link UserContext}
     * @author nza
     * @createTime 2021/3/7 12:20
     */
    private static UserContext createEmptyContext() {
        return new UserContext();
    }

    /**
     * 删除线程本地变量
     *
     * @author nza
     * @createTime 2021/3/7 13:16
     */
    public static void remove() {
        USER_CONTEXT.remove();
    }
}
