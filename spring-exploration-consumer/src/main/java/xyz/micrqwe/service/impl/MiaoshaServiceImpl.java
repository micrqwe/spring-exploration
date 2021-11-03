package xyz.micrqwe.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shaowenxing on 2017/5/23.
 */
@Service
@Scope("prototype")
public class MiaoshaServiceImpl {

    Lock lock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private AtomicBoolean locks = new AtomicBoolean(true);

    BlockingQueue queue = new LinkedBlockingQueue(10);

    public boolean tryLock() {
        return locks.compareAndSet(true, false);
    }

    public void unlock() {
        locks.set(true);
    }

    public Object miaosha() {
//        tryLock();
        lock.lock();
//        int age = cityMapper.queryAge(null);
//        if (age > 0) {
//            cityMapper.updateAge(null, 0);
//            lock.unlock();
//            return 1;
//        }
        lock.unlock();
        return 0;
    }
}
