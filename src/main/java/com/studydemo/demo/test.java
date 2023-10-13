package com.studydemo.demo;

/**
 * @author 孙浩林
 * @date: 10/8/23 15:02
 */
public class test {

    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");

        System.out.println(str1.equals(str2)); // 输出 true，因为内容相同
        System.out.println(str1.hashCode()); // 哈希码不同
        System.out.println(str2.hashCode()); // 哈希码不同
    }
}
