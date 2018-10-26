package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 同步栅栏测试3 CyclicBarrier
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */

@Slf4j
public class CyclicBarrierExample3 {
    //1.新建一个内存栅栏
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        // 当栅栏broken掉的时候，首先运行这里的逻辑，而后执行wait()下面的逻辑
        log.info("callback is running");
    });

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
                    race3(threadNum);
                } catch (Exception e) {
                    log.info("e:{}"+e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race3(int threadNum) throws InterruptedException, BrokenBarrierException {
        log.info("{} is already"+threadNum);
        cyclicBarrier.await();
        log.info("{} is start"+threadNum);
    }
}
