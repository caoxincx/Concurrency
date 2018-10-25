package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * @描述 尝试获取信号量，如果如果没有信号量等待一定的时间再获取
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
public class SemaphoreExample4 {
    private final static int threadPool = 20;

    public static void main(String[] args) {
        //1. 创建一个可变大小的线程池
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        //2. 创建三个信号量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadPool; i++){
            int threadNum = i;
            executorService.execute(()->{
                try {
                    // 尝试获取信号量，获取不到则线程执行结束(不会等待)
                    if (semaphore.tryAcquire(4000,TimeUnit.MILLISECONDS)){
                        // 临界区代码
                        test(threadNum);
                        // 释放信号量
                        semaphore.release(3);
                    }

                } catch (InterruptedException e) {
                   log.info("e:{}"+e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}"+threadNum);
        Thread.sleep(100);
    }
}
