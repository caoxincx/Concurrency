package it.caoxin.Concurrency.validate;

/**
 * @描述 验证volatile是否有原子性
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class VolatileAutomic implements Runnable{
    private static volatile int count = 0;
    @Override
    public void run() {
       count++;
    }
    public static int getCount() {
        return count;
    }
}
