package it.caoxin.Concurrency.validate;

/**
 * @描述 volatile 原子性测试,volatile不能保证原子性
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class VolatileAutomicTest {

    public static void main(String[] args) {
        VolatileAutomic[] volatileAutomics = new VolatileAutomic[5000];
        for (int i = 0; i < volatileAutomics.length; i++){
           volatileAutomics[i] = new VolatileAutomic();
           Thread thread = new Thread(volatileAutomics[i]);
           thread.start();
        }
        System.out.println("最后的值为："+VolatileAutomic.getCount());
    }
}
