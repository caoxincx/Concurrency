package it.caoxin.Concurrency.lock;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @描述 ReentrantReadWriterLock Test
 *      可重入读写锁，获取写锁需要读锁和写锁都不存在的时候才能获取
 *      很容易导致，写线程饥饿
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */
public class LockExample3 {
    private ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock();

    private Lock readLock = lock.readLock();
    private Lock writerLock = lock.writeLock();

    private TreeMap<Object,Object> treeMap = new TreeMap<>();

    /**
     * 在treeMap里面加上读锁
     * @param key
     */
    public Object get(Object key){
        readLock.lock();
        try {
            return treeMap.get(key);
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 在treeMap 写方法中加上写锁
     * @param key
     * @param value
     */
    public void put(Object key,Object value){
        writerLock.lock();
        try {
            treeMap.put(key,value);
        }finally {
            writerLock.unlock();
        }
    }

}
