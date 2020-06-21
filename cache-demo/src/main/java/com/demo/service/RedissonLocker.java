package com.demo.service;

import com.demo.connector.RedissonConnector;
import com.demo.interfacelock.AcquiredLockWorker;
import com.demo.interfacelock.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLocker implements DistributedLocker {
    private final static String LOCKER_PREFIX = "lock:";

    @Autowired
    RedissonConnector redissonConnector;

    @Override
    public <T> T lock(String resourceName, AcquiredLockWorker<T> worker) throws Exception {
        return lock(resourceName, worker, 100);
    }

    @Override
    public <T> T lock(String resourceName, AcquiredLockWorker<T> worker, int lockTime) throws Exception {
        RedissonClient redisson= redissonConnector.getClient();
        RLock lock = redisson.getLock(LOCKER_PREFIX + resourceName);
        // Wait for 100 seconds seconds and automatically unlock it after lockTime seconds
        boolean success = lock.tryLock(100, lockTime, TimeUnit.SECONDS);
        if (success) {
            try {
                return worker.invokeAfterLockAcquire();
            } finally {
                lock.unlock();
            }
        }
        throw new Exception();
    }
}