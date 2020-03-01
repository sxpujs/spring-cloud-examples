package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
public class NetflixEurekaClientProviderApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello world";
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/hello")
	public String hello(){
		List<String> services = discoveryClient.getServices();
		for(String s : services){
			log.info(s);
		}
		return "hello spring cloud!";
	}

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaClientProviderApplication.class, args);
	}

}
