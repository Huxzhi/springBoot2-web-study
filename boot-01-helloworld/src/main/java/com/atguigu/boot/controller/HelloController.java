package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestBody
//@Controller

@RestController
public class HelloController {

    @Autowired //自动注入、
    Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    @RequestMapping("/hello")
    public String handle01() {
        return "Hello, Spring Boot 2!";
    }
}
