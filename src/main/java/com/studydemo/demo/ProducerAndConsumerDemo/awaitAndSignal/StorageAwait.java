package com.studydemo.demo.ProducerAndConsumerDemo.awaitAndSignal;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StorageAwait {

    private final Lock lock = new ReentrantLock(true);
    // 仓库满的条件变量
    private final Condition full = lock.newCondition();
    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();
    private int MAX_SIZE = 10;
    private LinkedList<Object> list = new LinkedList<>();
    public void produce() {
        lock.lock();
        try {
            while (list.size() >= MAX_SIZE) {
                System.out.println("【生产者" + Thread.currentThread().getName() + "】仓库已满，等待消费");
                full.await();
            }
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
            empty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        lock.lock();
        try {
            while (list.size() == 0) {
                System.out.println("【消费者" + Thread.currentThread().getName() + "】仓库为空，等待生产");
                empty.await();
            }
            list.remove();
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + list.size());
            full.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}
