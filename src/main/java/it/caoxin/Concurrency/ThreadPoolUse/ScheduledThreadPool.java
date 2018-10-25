package it.caoxin.Concurrency.ThreadPoolUse;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @描述
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
public class ScheduledThreadPool {
    public static void main(String[] args) {
        //预定的线程池
        ScheduledExecutorService execute =
                Executors.newScheduledThreadPool(1);
//        // 三秒后执行
        execute.schedule(()->{
            log.warn("scheduled running");
        },3,TimeUnit.SECONDS);

        // 三秒后执行，每隔一秒后执行一次
        execute.scheduleAtFixedRate(()->{
            log.warn("scheduled running");
        },1,3,TimeUnit.SECONDS);

        // 三秒后执行，每隔5秒后执行一次
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("scheduled running");
            }
        },new Date(),5 * 1000);
    }
}
