package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

@Service
@Slf4j
public class RedisLock {
    private String lockKey = "redis_lock"; // 锁键
    protected long internalLockLeaseTime = 30000; // TTL
    private long timeout = 10000; // 获取锁的超时时间

    @Autowired
    private JedisPool jedisPool;

    // SET命令的参数
    private SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    // 加锁
    public boolean lock(String id) {
        Jedis jedis = jedisPool.getResource();
        Long start = System.currentTimeMillis();
        log.info("params=" + params);
        try {
            for (;;) {
                // SET命令返回OK，则证明获取锁成功
                String lock = jedis.set(lockKey, id, params);
                if ("OK".equals(lock)) {
                    return true;
                }
                // 否则循环等等，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
    }

    // 解锁
    public boolean unlock(String id) {
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(id));
            if ("1".equals(result.toString())) {
                return true;
            }
            return false;
        } finally {
            jedis.close();
        }
    }

}
