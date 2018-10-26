package it.caoxin.Concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @描述 Condition的测试
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */
@Slf4j
public class LockExample5 {
    public static void main(String[] args) {
        // 新建一个可重入锁
        ReentrantLock lock = new ReentrantLock();
        // 创建一个可重入锁的条件
        Condition condition = lock.newCondition();

        // 线程1
        new Thread(()->{
            try {
                lock.lock();
                log.info("wait signal");  //step:1
                condition.await();
            }catch (InterruptedException e) {
               log.error("e：{}"+e);
            }
            log.info("get signal");  //step:4
            lock.unlock();
        }).start();

        // 线程2
        new Thread(()->{
           lock.lock();
           log.info("get lock"); // step:2

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal"); //step:3
            lock.unlock();
        }).start();
    }
}
