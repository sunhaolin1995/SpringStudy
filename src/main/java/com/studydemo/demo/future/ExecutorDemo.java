package com.studydemo.demo.future;


import java.util.concurrent.*;

public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            //业务操作
            return "";
        }, threadPool1);
        //此时，如果future1中的业务操作已经执行完毕并返回，则该thenApply直接由当前main线程执行；否则，将会由执行以上业务操作的threadPool1中的线程执行。
        future1.thenApply(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });
        //使用ForkJoinPool中的共用线程池CommonPool
        future1.thenApplyAsync(value -> {
        //do something
            return value + "1";
        });
        //使用指定线程池
        future1.thenApplyAsync(value -> {
        //do something
            return value + "1";
        }, threadPool1);
    }

}
