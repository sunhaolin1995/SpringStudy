package com.studydemo.demo.lockAndCondition;

/**
 * @author 孙浩林
 * @date: 10/8/23 11:17
 */

public class ConditionBlockingExample {
    public static void main(String[] args) throws InterruptedException {
        springTest hello = new springTest();
        Thread WaitThread = new Thread(hello::waitingThread);

        Thread notifyThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hello.notifyThread();
            System.out.println("xiancheng huanxing lou");
        });

        WaitThread.start();
        WaitThread.join();
        notifyThread.start();
        notifyThread.join();

    }
}
