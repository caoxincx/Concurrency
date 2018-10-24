package it.caoxin.Concurrency.publish;


import it.caoxin.Concurrency.annotation.NotRecommend;
import it.caoxin.Concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @描述 this逃逸，参考java并发编程
 * 主线程在创建对象的时候，source.registerListener()开启了一个线程，
 * 该线程在EscapeExample1对象没有构造完成的时候引用了this对象的指针
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
@Slf4j
@NotRecommend
@ThreadNotSafe
public class EscapeExample1 {

    public EscapeExample1(EventSource source){
        source.registerListener((event)->{
            this.doSomething(event);
        });
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
    // 测试逃逸二是没有打印出来的
    public static void main(String[] args) {
        EscapeExample1 escapeExample1 = new EscapeExample1((eventListener)->{
           log.info("{}"+"测试逃逸1");
        });
    }
}
