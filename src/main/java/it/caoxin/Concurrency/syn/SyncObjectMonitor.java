package it.caoxin.Concurrency.syn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncObjectMonitor {
    //对象的monitor，作用于该对象
    public void objectSync1(String name){
        synchronized (this){
            for (int i = 0; i < 100; i++){
                log.info("name:{}"+name+"   i:"+ i);
            }
        }
    }
    public synchronized void objectSync2(String name){
        for (int i = 0; i < 100; i++){
            log.info("name:{}"+name+"   i:"+i);
        }
    }

    public static void main(String[] args) {
        SyncObjectMonitor syncObjectMonitor1 = new SyncObjectMonitor();
        SyncObjectMonitor syncObjectMonitor2 = new SyncObjectMonitor();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            syncObjectMonitor1.objectSync1("first");
        });

        executorService.execute(()->{
            syncObjectMonitor2.objectSync1("second");
        });
    }
}
