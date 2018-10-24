package it.caoxin.Concurrency.publish;

import it.caoxin.Concurrency.annotation.Recommend;
import it.caoxin.Concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @描述 防止对象逃逸
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@ThreadSafe
@Recommend
public class EscapeExample2 {
    private final EventListener listener;

    private EscapeExample2(){
        listener = ((event)->{
            this.doSomething(event);
        });
    }

    public static EscapeExample2 newInstance(EventSource source){
        // 这里创建第一个本对象
        EscapeExample2 safe = new EscapeExample2();
        // 这里才会创建第二个线程，这个线程如果调用EventListener里面的onEvent（）方法时
        // this对象已经成功的发布
        source.registerListener(safe.listener);
        return safe;
    }
    void doSomething(Event event){
        log.info("{}"+"测试逃逸2");
    }
    interface EventSource {
        void registerListener(EventListener eventListener);
    }
    interface EventListener {
        void onEvent(Event event);
    }
    interface Event{

    }
}
