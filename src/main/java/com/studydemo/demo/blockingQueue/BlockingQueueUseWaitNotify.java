package com.studydemo.demo.blockingQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author 孙浩林
 * @date: 10/8/23 16:13
 */
public class BlockingQueueUseWaitNotify {


    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myQueue.add(i);
                System.out.println("添加了" + i);
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int res = myQueue.get();
                System.out.println(" 消费了" + res);
            }
        });
        producerThread.start();
        consumerThread.start();
    }

}

class MyQueue {
    private final Object lock = new Object();
    private Queue queue = new ArrayDeque();
    private boolean first =true;
    private boolean done = false; // 添加结束标志

    public void add(int i) {
        synchronized (lock) {
            try {
                if (done) {
                    return; // 如果已经完成，则不再添加元素
                }
                if (!first){
                    lock.wait();
                }else {
                    queue.add(i);
                    first =false;
                    lock.notify(); // 通知消费者线程队列中已经有元素
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int get() {
        synchronized (lock) {
            try {
                if (done && queue.isEmpty()) {
                    return -1; // 如果已经完成且队列为空，返回-1表示没有可消耗的元素
                }
                if (first && queue.isEmpty()) {
                    lock.wait();
                } else {
                    int peek = (int)queue.peek();
                    queue.poll(); // 从队列中移除元素
                    first = true;
                    return peek;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return -1;
    }

    public void markAsDone() {
        synchronized (lock) {
            done = true;
            lock.notifyAll(); // 通知所有等待的线程结束
        }
    }

}






