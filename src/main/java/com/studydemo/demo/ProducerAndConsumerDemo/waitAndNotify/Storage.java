package com.studydemo.demo.ProducerAndConsumerDemo.waitAndNotify;

import java.util.LinkedList;
import java.util.Objects;

public class Storage {
    //仓库容量
    private final int MAX_SIZE =10;
    //
    private LinkedList<Object> list = new LinkedList();

    public void produce(){
        synchronized (list){
            while (list.size() +1 >MAX_SIZE){
                System.out.println("{生产者" + Thread.currentThread().getName()+"仓库已满");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.add(new Object());
            System.out.println("{生产者" + Thread.currentThread().getName()+"生产一个商品，现库存"+list.size());
            list.notifyAll();
        }
    }

    public void consume(){
        synchronized (list){
            while (list.size() == 0){
                System.out.println("消费者"+Thread.currentThread().getName()+"空间为空");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.remove();
            System.out.println("消费者"+Thread.currentThread().getName()+"消费了一个商品，现在的长度为"+list.size());
            list.notifyAll();
        }
    }



}
