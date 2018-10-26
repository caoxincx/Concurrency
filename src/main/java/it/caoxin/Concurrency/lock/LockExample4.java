package it.caoxin.Concurrency.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @描述 StampedLock 源码中乐观锁和悲观锁的使用
 * @创建人 caoxin
 * @创建时间 2018/10/26
 * @修改人和其它信息
 */
public class LockExample4 {
    class Point{
        private double x,y;
        private StampedLock lock = new StampedLock();

        // 简单的例子使用
        void move(double deltaX,double deltaY){
            long stamp = lock.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            }finally {
                lock.unlock(stamp);
            }
        }

        // 乐观锁读锁的使用
        double distanceFromOrigin(){
            // 获得一个读乐观锁
            long stamp = lock.tryOptimisticRead();
            double currentX = x,currentY = y;

            // 检查发生乐观锁后同时是否发生其他写锁
            if (!lock.validate(stamp)){
                //再次获得读悲观锁
                long readStamp = lock.readLock();

                try{
                    currentX = x;
                    currentY = y;
                }finally {
                    lock.unlock(readStamp);
                }
            }
            return Math.sqrt(currentX*currentX + currentY*currentY);
        }

        // 悲观读锁案例
        void moveIfAtOrigin(double newX,double newY){
            long stamp = lock.readLock();
            try {
                // 循环检测状态是否符合
                while (x == 0L && y == 0L){
                    // 将读锁转换成写锁
                    long writeStamp = lock.tryConvertToWriteLock(stamp);

                    // 确认是否转换成功
                    if (writeStamp != 0L){
                        stamp = writeStamp;
                        x = newX;
                        y = newY;
                        break;
                    }else {
                        // 转换不成功
                        lock.unlockRead(stamp);
                        // 显形进行写锁
                        stamp = lock.writeLock();
                    }
                }
            }finally {
                // 释放写锁或者读锁
                lock.unlock(stamp);
            }
        }
    }
}
