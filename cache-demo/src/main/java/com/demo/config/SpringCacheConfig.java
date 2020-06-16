package com.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author chao.cheng
 * @createTime 2020/5/9 3:29 下午
 * @description
 **/
@EnableCaching
@Configuration
public class SpringCacheConfig {

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("accountCache")));
        return cacheManager;
    }
}
