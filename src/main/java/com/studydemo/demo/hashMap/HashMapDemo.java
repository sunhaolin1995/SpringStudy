package com.studydemo.demo.hashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class HashMapDemo {


    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(15);
        for (int i = 0; i < 100; i++) {
            map.put(i,i);
        }
        for (Map.Entry child:map.entrySet()){
            if(Objects.equals(child.getKey(),5)){
                map.remove(child.getKey());
            }
        }
        Object o = map.get(1);
        System.out.println(map.size());
       // HashMap<Object, Object> map = new HashMap<>(15);
        for (int i = 0; i < 3; i++) {
            map.put(i,i);
        }
        Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Object, Object> entry = iterator.next();
            //判断条件，value，可以用entry.getKey进行判断Key值
            if(entry.getValue().equals(1)){
                //删除
                iterator.remove();
            }
            //删除后输出发现并没有立即删除掉，在第二次循环进入后会发现元素已删，
            //因为立即删掉的是it迭代器中的，第二次循环进入后重新获取长度，这也是
            //为什么要使用迭代器删除的原因
            System.out.println(map.toString());
        }
        System.out.println(map.size());


    }



}
