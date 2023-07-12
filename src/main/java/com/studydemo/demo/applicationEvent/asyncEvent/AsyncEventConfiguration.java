package com.studydemo.demo.applicationEvent.asyncEvent;

/**
 * @author 孙浩林
 * @date: 7/5/23 20:19
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 开启异步支持
 */
@Configuration
@EnableAsync
public class AsyncEventConfiguration implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
