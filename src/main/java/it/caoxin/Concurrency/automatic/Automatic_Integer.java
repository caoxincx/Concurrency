package it.caoxin.Concurrency.automatic;


import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @描述 AuticInteger的使用
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class Automatic_Integer {
    // 请求总数
    private static final int CLIENTTOAL = 5000;

    //同时并发执行的线程数
    private static int THREADTOTAL = 200;

    //创建一个原子类，AtomicInteger,并初始化为0
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(THREADTOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENTTOAL);

        for (int i = 0; i < CLIENTTOAL; i++){
            threadPool.execute(()->{
                try {
                    // 获取信号量
                    semaphore.acquire();
                    add();
                    // 释放信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        // 等待countDownLatch执行完后执行主线程
        countDownLatch.await();
        threadPool.shutdown();
        log.info("count:{}"+count);

    }

    public static void add(){
        count.incrementAndGet();
    }
}
