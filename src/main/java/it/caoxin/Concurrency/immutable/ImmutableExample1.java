package it.caoxin.Concurrency.immutable;

import com.google.common.collect.Maps;
import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @描述 不可变类
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadNotSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String  b = "b";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("map:{}"+map);
    }

    private void test(final int a){
//        形参中如果声明了该参数那么在该作用域里面就不可以修改该值
//        a = 1;
    }
}
