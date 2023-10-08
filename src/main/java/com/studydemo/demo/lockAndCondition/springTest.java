package com.studydemo.demo.lockAndCondition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

public class springTest {

    //锁
    private Lock lock = new ReentrantLock();
    //锁的条件
    private Condition condition = lock.newCondition();
    //是与否条件
    private boolean conditionTrue = false;

    public void waitingThread(){
        lock.lock();
        while (!conditionTrue) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 恢复中断状态
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }



    }

    public void notifyThread() {
        lock.lock();

        try {
            conditionTrue = true;
            condition.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }


}

