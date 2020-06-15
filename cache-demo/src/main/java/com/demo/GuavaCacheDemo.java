package com.demo;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class GuavaCacheDemo {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(20)
            .expireAfterAccess(20, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> removalNotify) {
                    log.info("有缓存数据被删除了:" + removalNotify.getKey() + "-" + removalNotify.getValue());
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    log.info("数据缓存加载:" + key);
                    return "test_" + key;
                }
            });

    public static String get(String key) {
        try {
            String value = cache.get(key);
            log.info("查询缓存:" + key);
            return value;
        } catch (ExecutionException e) {
            log.error("get value is error! key=" + key, e);
        }
        return null;
    }

    public static void remove(String key) {
        try {
            cache.invalidate(key);
        } catch (Exception e) {
            log.error("移除缓存失败! key=" + key, e);
        }
    }

    public static void removeAll() {
        try {
            cache.invalidateAll();
        } catch (Exception e) {
            log.error("移除全部缓存失败! ", e);
        }
    }

    public static void put(String key, String value) {
        try {
            cache.put(key, value);
            log.info("缓存保存成功! key=" + key + ", value=" + value);
        } catch (Exception e) {
            log.error("保存缓存失败! key=" + key + ", value=" + value, e);
        }
    }

    public static ConcurrentMap<String, String> viewCaches() {
        log.info("缓存中的数据：" + cache.asMap());
        return cache.asMap();
    }
}
