package com.studydemo.demo.rocketMq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author 孙浩林
 * @date: 6/16/23 13:40
 */
public class ConsumerMain {
    public static void main(String[] args) throws  Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("topicName","*");
        consumer.registerMessageListener(new MessageListenerConcurrently(){
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(list);
                System.out.println(consumeConcurrentlyContext);
                return null;
            }
        });
        consumer.start();
        System.out.println("消费者启动");
    }
}
