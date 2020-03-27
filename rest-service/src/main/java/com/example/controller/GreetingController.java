package com.example.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.service.Task;
import com.example.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    SpringContextHolder holder;


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        Task task1 = holder.getTask("fooTask");
        Task task2 = holder.getTask("barTask");

        task1.execute();
        task2.execute();

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}