package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 同步栅栏测试1 CyclicBarrier
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */

@Slf4j
public class CyclicBarrierExample1 {
    //1.新建一个内存栅栏
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        // 1.创建一个线程池
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        // 2.执行线程
        for (int i = 0; i < 10; i++){
            Thread.sleep(1000);

            //在这里让线程休眠一下，栅栏里面的线程数到达两个的时候，则栅栏会broken掉
            int threadNum = i;
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.info("e:{}"+e);
                }
            });
        }
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        log.info("{} is already"+threadNum);
        // 无限的等待时间
        cyclicBarrier.await();
        log.info("{} is continue"+threadNum);
    }
}
