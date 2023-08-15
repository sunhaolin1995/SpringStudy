package com.studydemo.demo.rocketMq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMQConsumerDemo {
    public static void main(String[] args) {
        try {
            // 创建消费者实例
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");

            // 设置 NameServer 地址
            consumer.setNamesrvAddr("localhost:9876");

            // 订阅主题和标签（Tag）
            consumer.subscribe("TopicTest", "*");

            // 注册消息监听器
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                    for (MessageExt message : messages) {
                        // 处理收到的消息
                        System.out.println("收到消息：" + new String(message.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            // 启动消费者
            consumer.start();

            System.out.println("消费者启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
