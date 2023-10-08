package com.studydemo.demo.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 孙浩林
 * @date: 10/8/23 15:46
 */
public class BlockingQueueExample {

    public static void main(String[] args) {
        /*//创建一个容量为 10 的 ArrayBlockingQueue
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // 创建生产者和消费者线程
        Thread producerThread = new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产"+i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerThread =new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Integer take = queue.take();
                    System.out.println("消费了"+take);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producerThread.start();
        consumerThread.start();*/
// 创建一个容量为 10 的 ArrayBlockingQueue
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // 创建生产者线程和消费者线程
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i); // 将元素放入队列
                    System.out.println(" 生产: " + i);
                    //Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = queue.take(); // 从队列中取出元素
                    System.out.println("消费: " + value);
                    //Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();
    }

}
