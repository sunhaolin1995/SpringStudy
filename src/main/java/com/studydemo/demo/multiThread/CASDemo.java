package com.studydemo.demo.multiThread;

/**
 * @author 孙浩林
 * @date: 5/23/23 13:08
 */

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static int nonCASCounter = 0;

    public static void main(String[] args) {
        // 创建两个线程，分别进行增加操作
        Thread casThread = new Thread(() -> {
            for (int i = 0; i < 30000; i++) {
                incrementCounterCAS();
            }
        });
        Thread casThread1 = new Thread(() -> {
            for (int i = 0; i < 30000; i++) {
                incrementCounterCAS();
            }
        });
        Thread casThread2 = new Thread(() -> {
            for (int i = 0; i < 40000; i++) {
                incrementCounterCAS();
            }
        });

        Thread nonCASThread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                incrementCounterNonCAS();
            }
        });
        Thread nonCASThread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                incrementCounterNonCAS();
            }
        });

        // 启动线程
        casThread.start();
        casThread1.start();
        casThread2.start();
        nonCASThread.start();
        nonCASThread1.start();

        try {
            // 等待两个线程执行完成
            casThread.join();
            casThread1.join();
            casThread2.join();
            nonCASThread.join();
            nonCASThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印最终结果
        System.out.println("Counter value (CAS): " + counter.get());
        System.out.println("Counter value (non-CAS): " + nonCASCounter);
    }

    private static void incrementCounterCAS() {
        int currentValue;
        int newValue;
        do {
            currentValue = counter.get();
            newValue = currentValue + 1;
        } while (!counter.compareAndSet(currentValue, newValue));
    }

    private static void incrementCounterNonCAS() {
        nonCASCounter++;
    }
}


