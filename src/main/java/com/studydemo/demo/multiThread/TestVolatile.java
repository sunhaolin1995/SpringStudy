package com.studydemo.demo.multiThread;

import java.util.concurrent.TimeUnit;

/**
 * @author 孙浩林
 * @date: 5/25/23 15:31
 */
public class TestVolatile {

    private static volatile boolean stop = false;

    public static void main(String[] args) {
        // Thread-A
        new Thread("Thread A") {
            @Override
            public void run() {
                while (!stop) {
                }
                System.out.println(Thread.currentThread() + " stopped");
            }
        }.start();

        // Thread-main
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread() + " after 1 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
    }

}
