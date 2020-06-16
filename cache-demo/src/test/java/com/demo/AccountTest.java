package com.demo;

import com.demo.Application;
import com.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chao.cheng
 * @createTime 2020/5/9 3:32 下午
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Slf4j
public class AccountTest {

    @Autowired
    private AccountService service;

    @Test
    public void test() throws Exception {

        service.saveAccountByName("abc");

        log.info("abc=" + service.getAccountByName("abc") + "");
        log.info("abc=" + service.getAccountByName("abc") + "");
        log.info("efg=" + service.getAccountByName("efg") + "");

    }
}
