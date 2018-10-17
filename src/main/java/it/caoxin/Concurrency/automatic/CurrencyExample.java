package it.caoxin.Concurrency.automatic;

import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @描述 使用Semaphore,CountDownLatch 模拟并发
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class CurrencyExample {
    // 请求总数
    private static final int CLIENTTOAL = 5000;

    //同时并发执行的线程数
    private static int THREADTOTAL = 1;

    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        //创建一个信号量
        final Semaphore semaphore = new Semaphore(CLIENTTOAL,true);

        final CountDownLatch countDownLatch = new CountDownLatch(THREADTOTAL);

        for (int i = 0; i < THREADTOTAL; i++){
            executor.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    // 当信号量为一个的时候，countDown()的操作需要在同步方法里面做
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("count:{}"+count);
                }
                // 在这里做才是正确的
                countDownLatch.countDown();
            });
            // countDown()执行时，countDown()数减1
            // 在这里做countDown()不能直接防止指令的重排序！！！
        }
        // 主线程等待countDownLatch.countDown()里面的数减少到0 才执行下面的语句
        countDownLatch.await();
        executor.shutdown();
        log.info("count:{}"+count);
    }

    public static void add(){
        count++;
    }
}
