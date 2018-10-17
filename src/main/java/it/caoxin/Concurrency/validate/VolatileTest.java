package it.caoxin.Concurrency.validate;

/**
 * @描述 Volatile可见性测试
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class VolatileTest {
    public static void main(String[] args) {
        VolatileTestThread volatileTestThread = new VolatileTestThread();
        Thread thread = new Thread(volatileTestThread);
        thread.start();
        //设置共享变量的值为false
        volatileTestThread.setFlag(false);
    }
}
