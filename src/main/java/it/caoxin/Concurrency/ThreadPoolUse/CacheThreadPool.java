package it.caoxin.Concurrency.ThreadPoolUse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 有缓存的线程池
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++){
            cacheThreadPool.execute(new ThreadPrintOwnInfo(i+i));
        }
        cacheThreadPool.shutdown();
    }
}
