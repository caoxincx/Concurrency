package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 aqs Abstract Queue Synchronize
 * CountDownLatchExample
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */

@Slf4j
public class CountDownLatchExample1 {
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
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("error:{}"+e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        // 等待其他线程直线完再执行,主线程再执行
        countDownLatch.await();
        log.info("finished");
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}"+threadNum);
        Thread.sleep(100);
    }

}
