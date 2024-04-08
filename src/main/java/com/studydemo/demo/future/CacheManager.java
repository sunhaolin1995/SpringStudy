package com.studydemo.demo.future;
import java.util.concurrent.*;

public class CacheManager {
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public String getValue(String key) {
        return cache.get(key);
    }

    public void updateCache(String key, String value) {
        cache.put(key, value);
    }

    public void updateCacheSynchronously(String key, String value) {
        updateCache(key, value);
        // 在这里执行其他同步的操作，例如数据库更新等
        new ThreadPoolExecutor(4,10,2000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1),new ThreadPoolExecutor.AbortPolicy());

    }

    public void updateCacheAsynchronously(String key, String value) {
        executor.submit(() -> {
            updateCache(key, value);
            // 在这里执行其他异步的操作，例如日志记录等


        });
    }

    public void shutdownExecutor() {
        executor.shutdown();
    }
}
