package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IHelloService
 * 配置服务提供者：provider 是服务提供者的 application.name
 */
@FeignClient("provider")
public interface IHelloService {

    @RequestMapping(value = "/hello")
    String hello();
}