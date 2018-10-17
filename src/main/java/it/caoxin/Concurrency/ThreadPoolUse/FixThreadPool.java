package it.caoxin.Concurrency.ThreadPoolUse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 固定大小的线程池
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class FixThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 50; i++){
            pool.execute(new ThreadPrintOwnInfo(i+1));
        }

        pool.shutdown();
    }
}
