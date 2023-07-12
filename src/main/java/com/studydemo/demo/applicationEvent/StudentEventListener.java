package com.studydemo.demo.applicationEvent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 孙浩林
 * @date: 7/5/23 20:02
 */
@Component
public class StudentEventListener {


    @Async
    @EventListener(condition = "#student.id != null")
    public void handleEvent(Student student){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(student);
    }
}

