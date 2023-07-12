package com.studydemo.demo.multiThread;

/**
 * @author 孙浩林
 * @date: 5/24/23 10:13
 * 可重入原理：加锁次数计数器什么是可重入？可重入锁？可重入：
 * （来源于维基百科）若一个程序或子程序可以“在任意时刻被中断然后操作系统调度执行另外一段代码，这段代码又调用了该子程序不会出错”，
 * 则称其为可重入（reentrant或re-entrant）的。即当该子程序正在运行时，执行线程可以再次进入并执行它，仍然获得符合设计时预期的结果。
 * 与多线程并发执行的线程安全不同，可重入强调对单个线程执行时重新进入同一个子程序仍然是安全的。
 * 可重入锁：又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁（前提锁对象得是同一个对象或者class）
 * ，不会因为之前已经获取过还没释放而阻塞。
 *
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo =  new SynchronizedDemo();
        demo.method1();


    }

    private synchronized void method1() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + ": method1()");
        Thread.sleep(1000);
        method2();
    }

    private synchronized void method2() throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+ ": method2()");
        Thread.sleep(1000);
        method3();
    }

    private synchronized void method3() throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+ ": method3()");
        Thread.sleep(1000);
    }

}
