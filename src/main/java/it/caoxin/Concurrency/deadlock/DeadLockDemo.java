package it.caoxin.Concurrency.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @描述 两个不同的线程竞争两个不同的资源
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */
@Slf4j
public class DeadLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        new Thread(()->{
            lock1.lock();
            log.info("{1} 获取了lock1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
            log.info("{1} 获取了lock2");
            lock2.unlock();
            lock1.unlock();
        }).start();

        new Thread(()->{
            lock2.lock();
            log.info("{2} 获取了lock2");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();
            log.info("{2} 获取了lock1");
            lock1.unlock();
            lock2.unlock();
        }).start();
    }
}
