package it.caoxin.Concurrency.LinkedThread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 join wait this Thread die
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class Join {
    /**
     * 控制线程的顺序执行
     */
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread1");
        }
    });

    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread3");
        }
    });

    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread3");
        }
    });


    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        //第一种方式
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();

        //第二种方式：创建只有一个线程的线程池
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);

    }
}
