package com.studydemo.demo.rocketMq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class FieldStatusProducer {
    private static final String TOPIC = "field_status_topic";
    private static final String NAMESRV_ADDR = "localhost:9876";

    public static void main(String[] args) throws MQClientException {
        // 创建RocketMQ生产者实例
        DefaultMQProducer producer = new DefaultMQProducer("field_status_producer");
        producer.setNamesrvAddr(NAMESRV_ADDR);

        try {
            // 启动生产者实例
            producer.start();

            // 模拟字段状态变化，发布消息到RocketMQ
            String tableName = "hhhhhhhhhhhhhhhhhhh";
            String fieldName = "dsadfasfasfasfascascascascsa";
            int oldValue = 0;
            int newValue = 1;

            // 构造消息内容
            String message = tableName + "," + fieldName + "," + oldValue + "," + newValue;

            // 创建RocketMQ消息对象
            Message mqMessage = new Message(TOPIC, message.getBytes());

            // 发送消息到RocketMQ
            SendResult sendResult = producer.send(mqMessage);

            System.out.println("Message sent successfully: " + sendResult.getMsgId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭RocketMQ生产者实例
            producer.shutdown();
        }
    }
}
