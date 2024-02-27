package com.studydemo.demo.limit;



import org.apache.rocketmq.logging.org.slf4j.Logger;
import org.apache.rocketmq.logging.org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 1.限流不够平滑。例如：限流是每秒3个，在第一毫秒发送了3个请求，达到限流，窗口剩余时间的请求都将会被拒绝，体验不好。
 *
 * 2.无法处理窗口边界问题。因为是在某个时间窗口内进行流量控制，所以可能会出现窗口边界效应，
 * 即在时间窗口的边界处可能会有大量的请求被允许通过，从而导致突发流量。
 * 即：如果第2到3秒内产生了150次请求，而第3到4秒内产生了150次请求，
 * 那么其实在第2秒到第4秒这两秒内，就已经发生了300次请求了，远远大于我们要求的3秒内的请求不要超过150次这个限制，如下图所示：
 */
public class FixedWindowRateLimiter {
    Logger logger = LoggerFactory.getLogger(FixedWindowRateLimiter.class);
    //时间窗口大小，单位毫秒
    long windowSize;
    //允许通过的请求数
    int maxRequestCount;
    //当前窗口通过的请求数
    AtomicInteger counter = new AtomicInteger(0);
    //窗口右边界
    long windowBorder;
    public FixedWindowRateLimiter(long windowSize, int maxRequestCount) {
        this.windowSize = windowSize;
        this.maxRequestCount = maxRequestCount;
        this.windowBorder = System.currentTimeMillis() + windowSize;
    }
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            logger.info("window reset");
            do {
                windowBorder += windowSize;
            } while (windowBorder < currentTime);
            counter = new AtomicInteger(0);
        }

        if (counter.intValue() < maxRequestCount) {
            counter.incrementAndGet();
            logger.info("tryAcquire success");
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}
