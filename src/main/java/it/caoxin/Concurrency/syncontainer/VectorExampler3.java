package it.caoxin.Concurrency.syncontainer;

import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @描述 同步容器的使用Vector 在for循环中便利并且删除测试
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class VectorExampler3 {
    /**
     * 测试在foreach中删除 ConcurrentModificationException
     *      * Vector中有一个值记录当前的Vector大小与原来的大小，如果不相等则抛出异常
     * @param vector
     */

    public static void testForeach(Vector<Integer> vector){
        for (Integer integer : vector){
            if (integer.equals(3)){
                vector.remove(integer);
            }
        }
    }

    /**
     * 测试在iterator中删除 ConcurrentModificationException
     * Vector中有一个值记录当前的Vector大小与原来的大小，如果不相等则抛出异常
     * @param vector
     */
    public static void testIterator(Vector<Integer> vector){
        Iterator<Integer> iterator =
                vector.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (next.equals(3)){
                vector.remove(next);
            }
        }
    }

    /**
     * 在For中删除是没问题的
     * @param vector
     */
    public static void testFor(Vector<Integer> vector){
        for (int i = 0; i < vector.size(); i++){
            if (vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> vector = new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(3);
//        testIterator(vector);
//        testForeach(vector);
        testFor(vector);

    }

}
