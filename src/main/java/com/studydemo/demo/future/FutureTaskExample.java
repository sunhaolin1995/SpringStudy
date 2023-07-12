package com.studydemo.demo.future;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = () -> {
            // 模拟耗时计算
            Thread.sleep(2000);
            return 2 + 3;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        // 创建线程执行任务
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("异步计算中...");

        // 阻塞等待计算结果
        int result = futureTask.get();

        System.out.println("计算结果: " + result);
    }
}
