package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    GreetingController greetingController;

    @Autowired
    public HelloController(GreetingController greetingController) {
        this.greetingController = greetingController;
    }

    public void test() {

    }
}
