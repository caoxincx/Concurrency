package it.caoxin.Concurrency.publish;

import it.caoxin.Concurrency.annotation.NotRecommend;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @描述 对象不安全的发布
 * 1.除了发布对象的线程外。
 *  其他线程看到某个对象的引用将会是空引用或者是之前的值。
 *  又或者是说当前的值是最新的，但是对象的状态还是无效的。
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@NotRecommend
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}"+Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}"+Arrays.toString(unsafePublish.getStates()));


    }
}
