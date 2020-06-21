package com.demo.controller;

import com.demo.service.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class LockController {

    @Autowired
    private RedisLock redisLock;

    int count = 0;

    @RequestMapping("/index")
    public String index() throws InterruptedException {
        int clientCount = 2;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                log.info("id=" + id);
                try {
                    redisLock.lock(id);
                    count++;
                } finally {
                    //redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);
        return "Hello";
    }

}
