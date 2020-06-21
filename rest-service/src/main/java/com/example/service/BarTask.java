package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("barTask")
public class BarTask implements Task {

    @Autowired
    FooTask fooTask;

    @Override
    public void execute() {
        System.out.println("Run BarTask");
    }

}