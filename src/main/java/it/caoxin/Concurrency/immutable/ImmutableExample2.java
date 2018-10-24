package it.caoxin.Concurrency.immutable;

import com.google.common.collect.Maps;
import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @描述 不可变Map的使用
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@ThreadSafe
@Slf4j
public class ImmutableExample2 {
    private static Map<Integer,Integer> map = Maps.newHashMap();
    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(4,5);//UnsupportedOperationException 抛出异常
        log.info("map:{}"+map);


    }
}
