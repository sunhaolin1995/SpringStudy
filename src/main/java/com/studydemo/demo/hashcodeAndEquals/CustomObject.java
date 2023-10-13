package com.studydemo.demo.hashcodeAndEquals;

import java.util.Objects;

/**
 * @author 孙浩林
 * @date: 10/10/23 15:32
 */
class CustomObject {
    private String value;

    public CustomObject(String value) {
        this.value = value;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomObject that = (CustomObject) o;
        return Objects.equals(value, that.value);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

class HashCodeEqualityExample {
    public static void main(String[] args) {
        CustomObject obj1 = new CustomObject("Hello");
        CustomObject obj2 = new CustomObject("Hello");

        System.out.println(obj1.equals(obj2)); // 输出 true，因为内容相同
        System.out.println(obj1.hashCode()); // 哈希码不同
        System.out.println(obj2.hashCode()); // 哈希码不同
        System.out.println(obj1);
        System.out.println(obj2);
    }
}

