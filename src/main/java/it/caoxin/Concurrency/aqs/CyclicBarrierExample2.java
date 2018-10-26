package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @描述 同步栅栏测试2 CyclicBarrier
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */

@Slf4j
public class CyclicBarrierExample2 {
    //1.新建一个内存栅栏
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        // 1.创建一个线程池
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        // 2.执行线程
        for (int i = 0; i < 10; i++){
            int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race1(threadNum);
                } catch (Exception e) {
                    log.info("e:{}"+e);
                }
            });
        }
    }

    private static void race1(int threadNum) throws InterruptedException, BrokenBarrierException {
        log.info("{} is already"+threadNum);
        try {
            // 当第一个线程到达时，等待两秒，然后这个栅栏就broken 不继续等待
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.info("e:{}"+e);
        }
        log.info("{} is start"+threadNum);
    }
}
