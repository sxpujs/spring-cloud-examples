package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fooTask")
public class FooTask implements Task {

    @Autowired
    BarTask barTask;

    @Override
    public void execute() {
        System.out.println("Run FooTask");
    }

}