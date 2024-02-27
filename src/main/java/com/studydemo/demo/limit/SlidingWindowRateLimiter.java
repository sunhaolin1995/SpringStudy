package com.studydemo.demo.limit;

import org.apache.rocketmq.logging.org.slf4j.Logger;
import org.apache.rocketmq.logging.org.slf4j.LoggerFactory;


/**
 * 优点：解决了固定窗口算法的窗口边界问题，避免突发流量压垮服务器。
 * 缺点：还是存在限流不够平滑的问题。
 * 例如：限流是每秒3个，在第一毫秒发送了3个请求，达到限流，
 * 剩余窗口时间的请求都将会被拒绝，体验不好。
 */
public class SlidingWindowRateLimiter {
    Logger logger = LoggerFactory.getLogger(FixedWindowRateLimiter.class);
    //时间窗口大小，单位毫秒
    long windowSize;
    //分片窗口数
    int shardNum;
    //允许通过的请求数
    int maxRequestCount;
    //各个窗口内请求计数
    int[] shardRequestCount;
    //请求总数
    int totalCount;
    //当前窗口下标
    int shardId;
    //每个小窗口大小，毫秒
    long tinyWindowSize;
    //窗口右边界
    long windowBorder;

    public SlidingWindowRateLimiter(long windowSize, int shardNum, int maxRequestCount) {
        this.windowSize = windowSize;
        this.shardNum = shardNum;
        this.maxRequestCount = maxRequestCount;
        this.shardRequestCount = new int[shardNum];
        this.tinyWindowSize = windowSize / shardNum;
        this.windowBorder = System.currentTimeMillis();
    }
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            logger.info("window reset");
            do {
                shardId = (++shardId) % shardNum;
                totalCount -= shardRequestCount[shardId];
                shardRequestCount[shardId] = 0;
                windowBorder += tinyWindowSize;
            } while (windowBorder < currentTime);
        }

        if (totalCount < maxRequestCount) {
            logger.info("tryAcquire success:{}", shardId);
            shardRequestCount[shardId]++;
            totalCount++;
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}
