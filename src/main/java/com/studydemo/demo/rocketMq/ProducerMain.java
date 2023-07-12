package com.studydemo.demo.rocketMq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author 孙浩林
 * @date: 6/16/23 10:26
 */
public class ProducerMain {
    public static void main( String[] args ) throws     Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ConsumerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        producer.setSendMsgTimeout(30000);
        for (int i = 0; i < 50000000; i++) {
            Message msg = new Message("topicName" ,("你好啊啊 11 哒哒哒哒实打实" + i).getBytes("UTF-8"));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
            Thread.sleep(3000);
        }
        System.out.println("生产者发送了");
        producer.shutdown();
    }
}
