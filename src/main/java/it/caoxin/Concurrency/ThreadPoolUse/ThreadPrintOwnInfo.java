package it.caoxin.Concurrency.ThreadPoolUse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @描述 打印自身信息的线程
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class ThreadPrintOwnInfo implements Runnable{
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
    private int num;

    public ThreadPrintOwnInfo(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName() +",time:"+format.format(new Date()));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
