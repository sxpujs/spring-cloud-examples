package com.example.service;

import org.springframework.stereotype.Service;

@Service("barTask")
public class BarTask implements Task {

    @Override
    public void execute() {
        System.out.println("Run BarTask");
    }

}