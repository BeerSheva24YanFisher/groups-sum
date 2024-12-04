package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadsPoolGroupSum extends ThreadsGroupSum{

    public ThreadsPoolGroupSum(int[][]groups) {
        super(groups);
    }

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
                executor.submit(tasks[i]);
            }
        } finally {
            executor.shutdown();
        }
    }

}