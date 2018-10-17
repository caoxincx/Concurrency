package it.caoxin.Concurrency.automatic;

import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @描述 AtomicReference的使用
 * @创建人 caoxin
 * @创建时间 2018/10/17
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class Automic_Reference {
    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
        // CAS操作
        atomicReference.compareAndSet(0, 2); // 2
        atomicReference.compareAndSet(0, 1); // 不执行
        atomicReference.compareAndSet(1, 3); // 不执行
        atomicReference.compareAndSet(2, 4); // 4
        atomicReference.compareAndSet(3, 5); // 不执行

        log.info("count:"+atomicReference.get());
    }
}
