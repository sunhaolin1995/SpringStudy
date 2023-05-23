package com.studydemo.demo.multiThread;

/**
 * @author 孙浩林
 * @date: 5/23/23 16:38
 * synchronized 代码块形式：手动指定锁定对象，也可是是this,也可以是自定义的锁
 */
public class SynchronizedObjectLock implements Runnable {
    static SynchronizedObjectLock instance = new SynchronizedObjectLock();

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {
            Thread t1 = new Thread(instance);
            Thread t2 = new Thread(instance);
            t1.start();
            t2.start();
        }

    }

    @Override
    public void run() {
        // 同步代码块形式——锁为this,两个线程使用的锁是一样的,线程1必须要等到线程0释放了该锁后，才能执行
        synchronized (this) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");

        }

    }
}
