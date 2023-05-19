package com.studydemo.demo.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 孙浩林
 * @date: 3/4/23 15:34
 */
public class AnnotationTest {

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PassWordUtils.class);

        PassWordUtils passWordUtils = new PassWordUtils();
        System.out.println(passWordUtils.encryptPassword("hello"));
    }

    public static void  trackUseCases(List<Integer> useCases,Class<?> cl){
        for (Method m :cl.getDeclaredMethods()) {
            //获得注解的对象
            UserCase.UserCases uc = (UserCase.UserCases) m.getAnnotation(UserCase.UserCases.class);
            if (uc != null){
                System.out.println("Found Use Case:" + uc.id() + "" +uc.description());
                useCases.remove(new Integer(uc.id()));
            }

        }

        for (int i: useCases) {
            System.out.println("warning:missing use case-" + i);
        }

    }

}
