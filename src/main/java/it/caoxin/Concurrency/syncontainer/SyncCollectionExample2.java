package it.caoxin.Concurrency.syncontainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @描述 同步集合Set
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@ThreadSafe
@Slf4j
public class SyncCollectionExample2 {
    // 请求总数
    private static final int CLIENTTOAL = 5000;

    //同时并发执行的线程数
    private static int THREADTOTAL = 200;

    private static Set<Integer> set =
            Collections.synchronizedSet(Sets.newHashSet());

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
                    addSycSet(count);
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

    public static void addSycSet(int i){
        set.add(i);
    }
}
