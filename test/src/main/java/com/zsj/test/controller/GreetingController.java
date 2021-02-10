package com.zsj.test.controller;

import com.zsj.test.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshangji
 * @since 2021/2/1 11:59
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @Autowired
    GreetingService service;

    @RequestMapping("/greeting")
    public String greeting() {
        return service.greet();
    }
}
