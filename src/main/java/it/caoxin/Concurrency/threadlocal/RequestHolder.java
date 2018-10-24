package it.caoxin.Concurrency.threadlocal;

/**
 * @描述 将变量作为该线程的变量
 * @创建人 caoxin
 * @创建时间 2018/10/24
 * @修改人和其它信息
 */
public class RequestHolder {
    private final static ThreadLocal<Long> REQUESTHOLDER =
            new ThreadLocal<>();

    /**
     * 在某个线程中放入该变量
     * @param id
     */
    public static void add(Long id){
        REQUESTHOLDER.set(id);
    }

    /**
     * 在某个线程中获取该变量
     * @return
     */
    public static Long get(){
        return REQUESTHOLDER.get();
    }

    /**
     * 在某个线程中删除该该变量
     */
    public static void remove(){
        REQUESTHOLDER.remove();
    }

}
