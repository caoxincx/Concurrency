package it.caoxin.Concurrency.lock;

import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @描述 无锁定测试程序
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class LockExample1 {

    // 请求数
    private final static int REQUESTCOUNT = 5000;

    // 并发量
    private final static int THREADCOUNT = 200;

    // 计数值
    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService threadPool =
                Executors.newCachedThreadPool();
        // 创建一个计数器阀锁
        final CountDownLatch countDownLatch = new CountDownLatch(REQUESTCOUNT);
        // 创建一个信号量
        final Semaphore semaphore = new Semaphore(THREADCOUNT);

        for (int i = 0; i < REQUESTCOUNT; i++){
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    notLockAdd();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        log.info("sum:{}"+sum);
    }

    private static void notLockAdd() {
        sum++;
    }
}
