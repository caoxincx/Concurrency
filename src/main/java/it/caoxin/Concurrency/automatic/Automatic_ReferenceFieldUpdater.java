package it.caoxin.Concurrency.automatic;

import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @描述 AtomicReferenceFieldUpdater 测试类中的某一个字段可以安全的更改
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class Automatic_ReferenceFieldUpdater {
    private static
    AtomicIntegerFieldUpdater<Automatic_ReferenceFieldUpdater> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Automatic_ReferenceFieldUpdater.class,"count");

    @Getter
    private volatile int count = new Integer(0);

    public static void main(String[] args) {
        Automatic_ReferenceFieldUpdater updater = new Automatic_ReferenceFieldUpdater();


        if (fieldUpdater.compareAndSet(updater,0,5)){
            log.info("count:"+updater.getCount());
        }
        if (fieldUpdater.compareAndSet(updater,0,5)){
            log.info("success:"+" can compareAndSet");
        }else {
            log.info("error:"+" can not compareAndSet");
        }

    }
}
