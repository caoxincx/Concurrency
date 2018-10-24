package it.caoxin.Concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @描述
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    //1.guava 不可变List的使用
    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3,4,5,6);

    //2.guava 不可变Set的使用
    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    //3.guava 不可变Map的使用 方式2
    private final static ImmutableMap<Integer,Integer> map1 =
            ImmutableMap.<Integer,Integer>builder().
                    put(1,2).
                    put(3,4).
                    put(5,6).
                    build();
    //4.guava 不可变Map的使用 方式2
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.of(1,2,3,4);


    public static void main(String[] args) {
        //1.禁止不给使用 报错
        list.add(2);

        //2.禁止不给使用 报错
        set.add(2);

        //3.禁止不给使用 报错
        map1.put(1,3);
    }
}
