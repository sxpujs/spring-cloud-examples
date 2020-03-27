package com.example.service;

import org.springframework.stereotype.Service;

@Service("fooTask")
public class FooTask implements Task {

    @Override
    public void execute() {
        System.out.println("Run FooTask");
    }

}