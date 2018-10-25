package it.caoxin.Concurrency.concurrencycontainer;

import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @描述 ConcurrentSkipListSet
 * ConcurrentSkipListMap 内部使用ConcurrentSkipListMap用于实现
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class CopyOnWriterSkipSetExample {
    // 请求总数
    private static final int CLIENTTOAL = 5000;

    //同时并发执行的线程数
    private static int THREADTOTAL = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

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
                    skipLinkedSet(count);
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

    public static void skipLinkedSet(int i){
        set.add(i);
    }
}
