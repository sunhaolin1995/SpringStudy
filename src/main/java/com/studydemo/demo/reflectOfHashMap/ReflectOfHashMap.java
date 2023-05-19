package com.studydemo.demo.reflectOfHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 孙浩林
 * @date: 3/9/23 14:03
 * 通过反射获取hashMap的bucket和key -value
 * Bucket 0: banana=5 orange=8
 * Bucket 1: apple=10
 * Bucket 10: mememe=8
 * Bucket 11: hello=8
 * Bucket 12: juice=8
 */
public class ReflectOfHashMap {
    public static void main(String[] args) {
        // 创建一个HashMap实例
        Map<String, Integer> hashMap = new HashMap<>();

        // 添加键值对
        hashMap.put("apple", 10);
        hashMap.put("banana", 5);
        hashMap.put("orange", 8);
        hashMap.put("juice", 8);
        hashMap.put("hello", 8);
        hashMap.put("mememe", 8);

        // 获取内部数组（桶）的引用
        Object[] buckets = getInternalBuckets((HashMap<String, Integer>) hashMap);

        // 遍历桶并显示内容
        for (int i = 0; i < buckets.length; i++) {
            Object bucket = buckets[i];
            if (bucket != null) {
                // 遍历桶内的链表并打印每个键值对
                System.out.print("Bucket " + i + ": ");
                while (bucket != null) {
                    // 判断链表节点是否为空
                    if (bucket instanceof Map.Entry) {
                        // 获取链表节点的键值对
                        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) bucket;
                        System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
                    }
                    // 获取链表节点的下一个节点
                    bucket = getNextNode(bucket);
                }
                System.out.println();
            }
        }
    }

    // 使用反射获取HashMap内部的buckets数组
    private static Object[] getInternalBuckets(HashMap<String, Integer> hashMap) {
        try {
            // 通过反射获取table字段（存储桶的数组）的值
            java.lang.reflect.Field tableField = HashMap.class.getDeclaredField("table");
            tableField.setAccessible(true);
            return (Object[]) tableField.get(hashMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 使用反射获取链表节点的下一个节点
    private static Object getNextNode(Object node) {
        try {
            java.lang.reflect.Field nextField = node.getClass().getDeclaredField("next");
            nextField.setAccessible(true);
            return nextField.get(node);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
