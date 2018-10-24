package it.caoxin.Concurrency.commonunsafe;

import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @描述 使用堆栈封闭的方法，解决SimpleDateFormat的错误使用
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class DateFormatExample2 {
    // 请求总数
    private static final int CLIENTTOAL = 5000;
    //同时并发执行的线程数
    private static int THREADTOTAL = 200;


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
                    dateformatt2();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        threadPool.shutdown();
    }

    public static void dateformatt2(){
        try {
            //SimpleDateFormat的正确使用
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20181024");
        } catch (ParseException e) {
            log.error("exception:{}"+e);
        }
    }
}
