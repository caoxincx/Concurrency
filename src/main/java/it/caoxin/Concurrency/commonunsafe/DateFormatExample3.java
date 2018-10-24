package it.caoxin.Concurrency.commonunsafe;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @描述 SimpleDateFormat的替代类joda-time的使用
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class DateFormatExample3 {
    // 请求总数
    private static final int CLIENTTOAL = 5000;
    //同时并发执行的线程数
    private static int THREADTOTAL = 200;
    //SimpleDateFormat 的替代类
    private static DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMMdd");

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
                   dateformatt3(count);
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

    public static void dateformatt3(int i){
       log.info("{},{}",i,DateTime.parse("20181024",dateTimeFormat));
    }
}
