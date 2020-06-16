package com.demo.service;

import com.demo.bean.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author chao.cheng
 * @createTime 2020/5/9 3:22 下午
 * @description
 **/
@Service
@Slf4j
public class AccountService {

    @Cacheable(value = "accountCache")
    public Account getAccountByName(String accountName) throws Exception {
        log.info("==缓存" + accountName + "进行到此刻===");
        Account account = getFromDB(accountName);

        if (account == null) {
            throw new Exception("account is null");
        } else {
            return account;
        }
    }

    public Account getFromDB(String accountName) {
        log.info("从数据库获取数据并且返回!");
        return new Account(accountName);
    }

    @CachePut(value = "accountCache", key = "#accountName")
    public void saveAccountByName(String accountName) {
        log.info("缓存数据 " + accountName + "已经保存成功");
    }
}
