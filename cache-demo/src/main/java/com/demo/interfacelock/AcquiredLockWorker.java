package com.demo.interfacelock;

public interface AcquiredLockWorker<T> {
    T invokeAfterLockAcquire() throws Exception;
}