package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @描述
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.创建一个未来的任务
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            log.info("callable do something");
            Thread.sleep(5000);
            return "DONE";
        });

        // 2.执行未来的任务
        new Thread(futureTask).start();

        // 3.在主线程模拟等待一会儿
        Thread.sleep(1000);

        // 4.获取未来任务返回的结果
        String ret = futureTask.get();

        // 5.查看返回结果
        log.info("ret:{}"+ret);

    }

}
