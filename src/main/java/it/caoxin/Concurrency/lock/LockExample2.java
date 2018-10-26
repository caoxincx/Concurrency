package it.caoxin.Concurrency.lock;

import com.sun.org.apache.regexp.internal.RE;
import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @描述 ReentrantLock Test
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */

@Slf4j
@ThreadSafe
public class LockExample2 {
    // 请求总数
    private final static int REQUESTTOTAL = 5000;

    // 并发量
    private final static int CONCURRENTTOTAL = 200;

    // 计数值
    private static int sum = 0;

    // 创建一个ReentrantLock
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService executors =
                Executors.newCachedThreadPool();

        // 计数器阀锁
        final CountDownLatch countDownLatch = new CountDownLatch(REQUESTTOTAL);

        // 信号量
        final Semaphore semaphore = new Semaphore(CONCURRENTTOTAL);


        for (int i = 0; i < REQUESTTOTAL; i++){
            executors.execute(()->{
                try {
                    semaphore.acquire();
                    reentrantLockAdd();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executors.shutdown();
        log.info("sum:{}"+sum);
    }

    private static void reentrantLockAdd() {
        reentrantLock.lock();
        try{
            sum++;
        }catch (Exception e){
           log.error("e:{}"+e);
        }finally {
            reentrantLock.unlock();
        }
    }
}
