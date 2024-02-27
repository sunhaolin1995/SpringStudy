package com.studydemo.demo;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.rocketmq.logging.org.slf4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }


    /***
     * 优缺点
     * 优点：
     * 1.可以处理突发流量：令牌桶算法可以处理突发流量。当桶满时，能够以最大速度处理请求。这对于需要处理突发流量的应用场景非常有用。
     *
     * 2.限制平均速率：在长期运行中，数据的传输率会被限制在预定义的平均速率（即生成令牌的速率）。
     *
     * 3.灵活性：与漏桶算法相比，令牌桶算法提供了更大的灵活性。例如，可以动态地调整生成令牌的速率。
     * 缺点：
     * 1.可能导致过载：如果令牌产生的速度过快，可能会导致大量的突发流量，这可能会使网络或服务过载。
     *
     * 2.需要存储空间：令牌桶需要一定的存储空间来保存令牌，可能会导致内存资源的浪费。
     *
     * 3.实现稍复杂：相比于计数器算法，令牌桶算法的实现稍微复杂一些。
     */
    @Test
    public void acquireTest() {
        //每秒固定生成5个令牌
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            double time = rateLimiter.acquire();
            System.out.println(time);
           // logger.info("等待时间：{}s", time);
        }
    }

}
