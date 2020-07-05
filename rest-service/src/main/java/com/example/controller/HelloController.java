package com.example.controller;

import com.example.service.AccessLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/access")
    public String access() {
        if (accessLimitService.tryAcquire()) {
            log.info("start");
            // 模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "access success [" + LocalDateTime.now() + "]";
        } else {
            //log.warn("限流");
            return "access limit [" + LocalDateTime.now() + "]";
        }
    }
}