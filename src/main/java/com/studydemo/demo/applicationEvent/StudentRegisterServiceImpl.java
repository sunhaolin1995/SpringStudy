package com.studydemo.demo.applicationEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 孙浩林
 * @date: 7/5/23 20:02
 */
@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void register() {
        Student student = new Student();
        student.setId(1);
        student.setName("tom");
        applicationEventPublisher.publishEvent(student);
        System.out.println("结束了");
    }
}

