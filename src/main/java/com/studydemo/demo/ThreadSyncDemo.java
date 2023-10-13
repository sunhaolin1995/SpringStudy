package com.studydemo.demo;

/**
 * @author 孙浩林
 * @date: 10/10/23 14:00
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadSyncDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);
        CyclicBarrier barrier = new CyclicBarrier(threadCount);

        System.out.println("Using CountDownLatch:");
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Worker(latch)).start();
        }

        // 主线程等待所有工作线程完成
        latch.await();

        System.out.println("All threads using CountDownLatch have finished.");

        System.out.println("\nUsing CyclicBarrier:");
        for (int i = 0; i < threadCount; i++) {
            new Thread(new BarrierWorker(barrier)).start();
        }
    }
}

class Worker implements Runnable {
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " has finished");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BarrierWorker implements Runnable {
    private final CyclicBarrier barrier;

    public BarrierWorker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is ready");
            barrier.await(); // 等待其他线程都准备好
            System.out.println(Thread.currentThread().getName() + " has started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

