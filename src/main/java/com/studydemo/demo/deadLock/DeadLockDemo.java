package com.studydemo.demo.deadLock;

/**
 * @author 孙浩林
 * @date: 10/9/23 16:21
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                System.out.println("Object1在 thread1  被锁住嘞");
                synchronized (object2) {
                    System.out.println("Object2 在 thread1 被锁住嘞");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                System.out.println("Object2在 thread2  被锁住嘞");
                synchronized (object1) {
                    System.out.println("Object1 在 thread2 被锁住嘞");
                }
            }
        });
        thread1.start();
        thread2.start();
    }

}
