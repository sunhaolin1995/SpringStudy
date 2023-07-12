package com.studydemo.demo.applicationEvent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 孙浩林
 * @date: 7/5/23 20:03
 */
@RestController
@RequestMapping("/event")

public class EventListenerController {
    @Resource
    private StudentRegisterService studentEventRegisterService;


    @GetMapping("/registerUser")
    public void register()  {
        try {
            studentEventRegisterService.register();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

