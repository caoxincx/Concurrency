package it.caoxin.Concurrency.ThreadPoolUse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 线程池中只有一个可以使用的线程
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
public class SingleThreadPool {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

}
