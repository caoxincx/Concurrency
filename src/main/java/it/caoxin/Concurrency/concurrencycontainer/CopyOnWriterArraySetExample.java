package it.caoxin.Concurrency.concurrencycontainer;

import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @描述 CopyOnWriterArraySet内部
 * 使用了一个CopyOnWriterArrayList来实现对应的逻辑
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class CopyOnWriterArraySetExample {
    // 请求总数
    private static final int CLIENTTOAL = 5000;

    //同时并发执行的线程数
    private static int THREADTOTAL = 200;

    private static Set set = new CopyOnWriteArraySet();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(THREADTOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENTTOAL);

        for (int i = 0; i < CLIENTTOAL; i++){
            final int count = i;
            threadPool.execute(()->{
                try {
                    // 获取信号量
                    semaphore.acquire();
                    copyOnWriterSet(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        threadPool.shutdown();
        log.info("size:{}"+set.size());
    }

    public static void copyOnWriterSet(int i){
        set.add(i);
    }
}
