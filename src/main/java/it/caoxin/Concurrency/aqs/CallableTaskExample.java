package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @描述 Callable Test
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
public class CallableTaskExample {

    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "DONE";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        // 1.创建一个线程池
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        // 2.创建一个未来的任务
        Future<String> future =
                executorService.submit(new MyCallable());

        // 3.主线程假装在左一些其他东西
        log.info("main thread do something else...");

        // 4.主线程休眠
        Thread.sleep(1000);

        // 5.主线程获取Future任务的返回值,等待future任务执行完
        // 如果等待future任务没有完成则结束并抛出异常java.util.concurrent.TimeoutException
        String ret = future.get(2000,TimeUnit.MILLISECONDS);

        // 6.打印出future任务的结果
        log.info("ret:{}"+ret);

        // 7.关闭线程池
        executorService.shutdown();
    }
}
