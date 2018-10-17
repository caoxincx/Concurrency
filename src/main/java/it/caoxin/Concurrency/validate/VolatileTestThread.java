package it.caoxin.Concurrency.validate;

/**
 * @描述 Volatile可见性测试
 * @创建人 caoxin
 * @创建时间 2018/10/16
 * @修改人和其它信息
 */
public class VolatileTestThread implements Runnable {
    private volatile boolean flag = false;
    @Override
    public void run() {
        this.flag = true;
        while(this.flag){
            System.out.println("子thread:"+Thread.currentThread().getName());
        }
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
