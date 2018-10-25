package it.caoxin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @描述 RecursiveTask
 * @创建人 caoxin
 * @创建时间 2018/10/25
 * @修改人和其它信息
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer>{

    // 两个线程数
    private final static int threadPool = 2;

    private int start;

    private int end;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= threadPool;
        //如果任务小于等于threadPool，则计算
        if (canCompute){
            for(int i = start; i < end; i++){
                sum += i;
            }
        }else {
            int middle = (start + end) / 2;
            // 左边任务
            ForkJoinTaskExample leftTask
                    = new ForkJoinTaskExample(start, middle);
            // 右边任务
            ForkJoinTaskExample rightTask
                    = new ForkJoinTaskExample(middle+1, end);

            // 执行左边任务
            leftTask.fork();
            // 执行右边任务
            rightTask.fork();

            // 等待任务执行完，并合并任务结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        // 创建一个并行语句块池
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成一个计算任务
        ForkJoinTaskExample forkJoinTaskExample =
                new ForkJoinTaskExample(1, 100);

        // 执行任务
        ForkJoinTask<Integer> ret = forkJoinPool.submit(forkJoinTaskExample);

        try {
            Integer sum = ret.get();
            log.info("sum:{}"+sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        forkJoinPool.shutdown();

    }
}
