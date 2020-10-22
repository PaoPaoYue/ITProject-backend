package com.ypp.itproject.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}
