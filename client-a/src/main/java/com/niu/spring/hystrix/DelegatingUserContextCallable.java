package com.niu.spring.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.niu.spring.utils.UserContext;
import com.niu.spring.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 自定义线程处理逻辑
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 13:40]
 * @createTime [2021/03/07 13:40]
 */
@Slf4j
public class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    /**
     * 方法会在被 {@link HystrixCommand} 注解保护的方法之前调用
     *
     * @author nza
     * @createTime 2021/3/7 13:58
     */
    @Override
    public V call() throws Exception {
        // 设置当前线程的上下文对象
        UserContextHolder.setUserContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
            log.info("删除 hystrix 线程本地变量, currentIp: {}", UserContextHolder.getContext().getCurrentIp());
            UserContextHolder.remove();
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
