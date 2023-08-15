package com.studydemo.demo.rocketMq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketMQProducerDemo {
    public static void main(String[] args) {
        try {
            // 创建生产者实例
            DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");

            // 设置 NameServer 地址
            producer.setNamesrvAddr("localhost:9876");

            // 启动生产者
            producer.start();

            // 创建消息对象
            Message message = new Message("TopicTest", "TagA", "Hello, RocketMQ!".getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 发送消息
            producer.send(message);

            // 关闭生产者
            producer.shutdown();

            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

