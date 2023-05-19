package com.studydemo.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 孙浩林
 * @date: 3/4/23 15:17
 */
public class UserCase {


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserCases{
        public String id();

        public String description() default "no description";
    }

}
