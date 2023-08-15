package com.studydemo.demo.fromZeroPlusToOneHundred;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AddAndPrintDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger number = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(new AddTask(number,50,latch));
        Thread thread2 = new Thread(new AddTask(number,50,latch));

        thread1.start();
        thread2.start();

        latch.await();
        System.out.println("REsult"+ number.get());
    }
}

class AddTask implements Runnable{
    private AtomicInteger number;

    private int times;
    private CountDownLatch latch;

    public AddTask(AtomicInteger number, int times, CountDownLatch latch) {
        this.number = number;
        this.times = times;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            number.incrementAndGet();
        }
        latch.countDown();
    }
}


