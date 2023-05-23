package com.studydemo.demo.multiThread;

/**
 * @author 孙浩林
 * @date: 5/23/23 10:25
 */
public class VolatileExample {
    private static boolean flagWithoutVolatile = false;
    private static volatile boolean flagWithVolatile = false;

    public static void main(String[] args) throws InterruptedException {
        // 不使用volatile关键字
        Thread thread1 = new Thread(() -> {
            int count = 0;
            while (!flagWithoutVolatile ) {
                count++;
            }
            System.out.println("Thread1: Flag为true无volitile，循环次数：" + count);
        });

        // 使用volatile关键字
        Thread thread2 = new Thread(() -> {
            int count = 0;
            while (!flagWithVolatile ) {
                count++;
            }
            System.out.println("Thread2: Flag为true有volitile，循环次数：" + count);
        });

        thread1.start();
        thread2.start();

        // 等待一段时间后将flag置为true
        Thread.sleep(1000);

        // 不使用volatile关键字
        flagWithoutVolatile = true;

        // 使用volatile关键字
        flagWithVolatile = true;

        // 中断线程
        thread1.interrupt();
        thread2.interrupt();

        // 等待线程执行完毕
        thread1.join();
        thread2.join();
    }
}



