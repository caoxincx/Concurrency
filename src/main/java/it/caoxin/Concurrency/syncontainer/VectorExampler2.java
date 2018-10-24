package it.caoxin.Concurrency.syncontainer;

import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;


/**
 * @描述 同步容器的使用Vector,并发获取和删除(不安全) array out of index
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class VectorExampler2 {

    private static List<Integer> vector = new Vector();

    public static void main(String[] args) throws InterruptedException {
        while(true){
            // 添加
            for (int i = 0; i < 10; i++){
                vector.add(i);
            }
            // 删除
            Thread thread1 = new Thread(()->{
                for (int i = 0; i < vector.size(); i++){
                    vector.remove(i);
                }
            });
            //获取
            Thread thread2 = new Thread(()->{
                for (int i = 0; i < vector.size(); i++){
                    vector.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }

    }

}
