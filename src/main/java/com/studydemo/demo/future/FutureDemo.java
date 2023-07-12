package com.studydemo.demo.future;

import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 孙浩林
 * @date: 7/1/23 16:34
 */
public class FutureDemo {
/*
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<String> future1 = guavaExecutor.submit(() -> {
            //step 1
            System.out.println("执行step 1");
            return "step1 result";
        });
        ListenableFuture<String> future2 = guavaExecutor.submit(() -> {
            //step 2
            System.out.println("执行step 2");
            return "step2 result";
        });
        ListenableFuture<List<String>> future1And2 = Futures.allAsList(future1, future2);
        Futures.addCallback(future1And2, new FutureCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                System.out.println(result);
                ListenableFuture<String> future3 = guavaExecutor.submit(() -> {
                    System.out.println("执行step 3");
                    return "step3 result";
                });
                Futures.addCallback(future3, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    }
                }, guavaExecutor);
            }

            @Override
            public void onFailure(Throwable t) {
            }}, guavaExecutor);
    }*/

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(() -> {
            Thread.sleep(2000);
            return 2 + 2;
        });

        System.out.println("异步计算中");
        Integer res = submit.get();
        System.out.println("结果为"+5);
        executorService.shutdown();
    }


}
