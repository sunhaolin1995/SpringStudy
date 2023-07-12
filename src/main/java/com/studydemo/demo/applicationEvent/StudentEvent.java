package com.studydemo.demo.applicationEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author 孙浩林
 * @date: 7/5/23 19:21
 */
public class StudentEvent extends ApplicationEvent {
    private String name;
    private String msg;
    public StudentEvent (Object source){
        super(source);
    }
    public StudentEvent (Object source, String name, String msg) {
        super(source);
        this.name = name;
        this.msg = msg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
