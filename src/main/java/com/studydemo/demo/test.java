package com.studydemo.demo;

/**
 * @author 孙浩林
 * @date: 10/8/23 15:02
 */
public class test {

    public static void main(String[] args) {
        Object lock = new Object();

        synchronized (lock) {
            try {
                System.out.println("等着被唤醒");
                lock.wait(); // 在5秒内等待被唤醒
                System.out.println("被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
