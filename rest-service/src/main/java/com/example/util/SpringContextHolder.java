package com.example.util;

import com.example.service.Task;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder extends ApplicationObjectSupport {

    public Task getTask(String beanName){
        return super.getApplicationContext().getBean(beanName , Task.class);
    }
}