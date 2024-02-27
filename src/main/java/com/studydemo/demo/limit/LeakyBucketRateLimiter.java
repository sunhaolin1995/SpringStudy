package com.studydemo.demo.limit;

import org.apache.rocketmq.logging.org.slf4j.Logger;
import org.apache.rocketmq.logging.org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 优缺点
 * 优点：
 * 1.平滑流量。由于漏桶算法以固定的速率处理请求，可以有效地平滑和整形流量，避免流量的突发和波动（类似于消息队列的削峰填谷的作用）。
 *
 * 2.防止过载。当流入的请求超过桶的容量时，可以直接丢弃请求，防止系统过载。
 * 缺点：
 * 1.无法处理突发流量：由于漏桶的出口速度是固定的，无法处理突发流量。例如，即使在流量较小的时候，也无法以更快的速度处理请求。
 *
 * 2.可能会丢失数据：如果入口流量过大，超过了桶的容量，那么就需要丢弃部分请求。在一些不能接受丢失请求的场景中，这可能是一个问题。
 *
 * 3.不适合速率变化大的场景：如果速率变化大，或者需要动态调整速率，那么漏桶算法就无法满足需求。
 *
 * 4.资源利用率：不管当前系统的负载压力如何，所有请求都得进行排队，即使此时服务器的负载处于相对空闲的状态，这样会造成系统资源的浪费。
 */
public class LeakyBucketRateLimiter {
    Logger logger = LoggerFactory.getLogger(LeakyBucketRateLimiter.class);
    //桶的容量
    int capacity;
    //桶中现存水量
    AtomicInteger water = new AtomicInteger();
    //开始漏水时间
    long leakTimestamp;
    //水流出的速率，即每秒允许通过的请求数
    int leakRate;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
    }

    public synchronized boolean tryAcquire() {
        //桶中没有水， 重新开始计算
        if (water.get() == 0) {
            logger.info("start leaking");
            leakTimestamp = System.currentTimeMillis();
            water.incrementAndGet();
            return water.get() < capacity;
        }
        //先漏水，计算剩余水量
        long currentTime = System.currentTimeMillis();
        int leakedWater = (int) ((currentTime - leakTimestamp) / 1000 * leakRate);
        logger.info("lastTime:{}, currentTime:{}. LeakedWater:{}", leakTimestamp, currentTime, leakedWater);
        //可能时间不足，则先不漏水
        if (leakedWater != 0) {
            int leftWater = water.get() - leakedWater;
            //可能水已漏光。设为0
            water.set(Math.max(0, leftWater));
            leakTimestamp = System.currentTimeMillis();
        }
        logger.info("剩余容量:{}", capacity - water.get());
        if (water.get() < capacity) {
            logger.info("tryAcquire sucess");
            water.incrementAndGet();
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}
