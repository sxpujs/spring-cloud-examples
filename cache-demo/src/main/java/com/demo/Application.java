package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        //GuavaCacheDemo demo = (GuavaCacheDemo) SpringApplication.run(Application.class, args).getBean("guavaCacheDemo");
        //GuavaCacheDemo.put("abc", "123");
        //GuavaCacheDemo.get("abc");
        //GuavaCacheDemo.remove("abc");
        SpringApplication.run(Application.class, args);
    }

}
