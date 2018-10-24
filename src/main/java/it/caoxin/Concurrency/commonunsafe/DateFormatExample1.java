package it.caoxin.Concurrency.commonunsafe;

import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @描述 验证DateFormatExample1使用时候的错误
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class DateFormatExample1 {
    // 请求总数
    private static final int CLIENTTOAL = 5000;
    //同时并发执行的线程数
    private static int THREADTOTAL = 200;
    //SimpleDateFormat的错误使用
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
                   dateformatt1();
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

    public static void dateformatt1(){
        try {
            simpleDateFormat.parse("20181024");
        } catch (ParseException e) {
            log.error("exception:{}"+e);
        }
    }
}
