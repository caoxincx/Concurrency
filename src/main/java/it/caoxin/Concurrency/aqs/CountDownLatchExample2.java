package it.caoxin.Concurrency.aqs;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @描述 aqs Abstract Queue Synchronize
 * CountDownLatchExample2
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */

@Slf4j
public class CountDownLatchExample2 {
    //两百个线程
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        // 1.创建一个线程池
        ExecutorService executorService =
                Executors.newCachedThreadPool();
        // 2.创建一个倒计数阀锁
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++){
            int threadNum = i;
            executorService.execute(()->{
                try {
                    test2(threadNum);
                } catch (InterruptedException e) {
                    log.error("error:{}"+e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //等待多少毫秒主线程结束
        countDownLatch.await(2,TimeUnit.MILLISECONDS);
        log.info("finished");
        executorService.shutdown();

    }

    private static void test2(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}"+threadNum);
    }

}
