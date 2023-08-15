package com.studydemo.demo.future;

/**
 * @author 孙浩林
 * @date: 7/20/23 10:34
 */
import java.util.HashMap;
import java.util.concurrent.*;

public class CompletableFutureDemo1 {

    public static void main(String[] args) throws Qualification1Exception, Qualification2Exception, Qualification3Exception, Qualification4Exception {
    String s;
        HashMap ha;
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



        // 模拟异步校验四个资格
        CompletableFuture<Boolean> qualification1 = CompletableFuture.supplyAsync(() -> {
            try {
                return checkQualification1();
            } catch (Qualification1Exception e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Boolean> qualification2 = CompletableFuture.supplyAsync(() -> {
            try {
                return checkQualification2();
            } catch (Qualification2Exception e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Boolean> qualification3 = CompletableFuture.supplyAsync(() -> {
            try {
                return checkQualification3();
            } catch (Qualification3Exception e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Boolean> qualification4 = CompletableFuture.supplyAsync(() -> {
            try {
                return checkQualification4();
            } catch (Qualification4Exception e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 使用allOf等待所有资格校验完成
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(qualification1, qualification2, qualification3, qualification4);

        // 处理所有资格校验结果
        try {
            allOfFuture.get(); // 等待所有资格校验完成
            if (qualification1.get() && qualification2.get() && qualification3.get() && qualification4.get()) {
                // 所有资格校验通过，执行业务逻辑
                doBusinessLogic();
            } else {
                // 抛出异常报错
                throw new IllegalArgumentException("资格校验未通过");
            }
        } catch (InterruptedException | ExecutionException e) {
            // 异常处理
            Throwable throwable = e.getCause(); // 获取异常原因
            if (throwable instanceof Qualification1Exception) {
                // 资格1校验未通过，抛出自定义异常Qualification1Exception
                throw (Qualification1Exception) throwable;
            } else if (throwable instanceof Qualification2Exception) {
                // 资格2校验未通过，抛出自定义异常Qualification2Exception
                throw (Qualification2Exception) throwable;
            } else if (throwable instanceof Qualification3Exception) {
                // 资格3校验未通过，抛出自定义异常Qualification3Exception
                throw (Qualification3Exception) throwable;
            } else if (throwable instanceof Qualification4Exception) {
                // 资格4校验未通过，抛出自定义异常Qualification4Exception
                throw (Qualification4Exception) throwable;
            } else {
                // 其他异常处理
                e.printStackTrace();
            }
        }
    }

    // 模拟资格校验方法
    private static boolean checkQualification1() throws Qualification1Exception, InterruptedException {
        // 模拟资格校验，返回true或false
        boolean result = true;
        System.out.println("第一个资格校验");
        Thread.sleep(5000);
        if (!result) {
            throw new Qualification1Exception("资格1校验未通过");
        }
        return result;
    }

    private static boolean checkQualification2() throws Qualification2Exception, InterruptedException {
        // 模拟资格校验，返回true或false
        boolean result = true;
        Thread.sleep(4000);
        System.out.println("第2个资格校验");
        if (!result) {
            throw new Qualification2Exception("资格2校验未通过");
        }
        return result;
    }

    private static boolean checkQualification3() throws Qualification3Exception, InterruptedException {
        // 模拟资格校验，返回true或false
        boolean result = true;
        Thread.sleep(3000);
        System.out.println("第3个资格校验");
        if (!result) {
            throw new Qualification3Exception("资格3校验未通过");
        }
        return result;
    }

    private static boolean checkQualification4() throws Qualification4Exception, InterruptedException {
        // 模拟资格校验，返回true或false
        boolean result = true;
        Thread.sleep(2000);
        System.out.println("第4个资格校验");
        if (!result) {
            throw new Qualification4Exception("资格4校验未通过");
        }
        return result;
    }

    // 模拟业务逻辑方法
    private static void doBusinessLogic() {
        System.out.println("执行业务逻辑...");
    }
}

// 自定义异常类
class Qualification1Exception extends Exception {
    public Qualification1Exception(String message) {
        super(message);
    }
}

class Qualification2Exception extends Exception {
    public Qualification2Exception(String message) {
        super(message);
    }
}

class Qualification3Exception extends Exception {
    public Qualification3Exception(String message) {
        super(message);
    }
}

class Qualification4Exception extends Exception {
    public Qualification4Exception(String message) {
        super(message);
    }
}


