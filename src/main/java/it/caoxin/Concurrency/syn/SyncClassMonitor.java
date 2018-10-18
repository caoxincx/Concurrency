package it.caoxin.Concurrency.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncClassMonitor {
    public static void classMoniter1(String name){
        synchronized (SyncClassMonitor.class){
            for (int i = 0; i < 100; i++){
                System.out.println("name:{}"+name+"   i:"+i);
            }
        }
    }

    public synchronized static void classMoniter2(String name){
        for (int i = 0; i < 100; i++){
            System.out.println("name:{}"+name+"   i:"+i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(()->{
            classMoniter1("first");
        });
        executor.execute(()->{
            classMoniter2("seconds");
        });
    }
}
