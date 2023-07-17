package com.studydemo.demo.future;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(() -> 3.14);

        CompletableFuture<Object> allFutures = CompletableFuture.anyOf(future1, future2, future3);

        allFutures.thenRun(() -> {
            System.out.println("All futures completed");
            Integer result1 = future1.join();
            String result2 = future2.join();
            Double result3 = future3.join();
            System.out.println("Result 1: " + result1);
            System.out.println("Result 2: " + result2);
            System.out.println("Result 3: " + result3);
        });

    }
}
