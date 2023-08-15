package com.studydemo.demo;


import com.studydemo.demo.entity.Person;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ForkJoinPool;

public class springTest {

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        int defaultPoolSize = ForkJoinPool.commonPool().getParallelism();

        System.out.println("CPU核心数: " + cpuCores);
        System.out.println("默认ForkJoinPool大小: " + defaultPoolSize);
    }



}
