package ec.edu.uce.practica.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManaged {
    private static final int THREAD_POOL_SIZE = 3;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static void shutdown() {
        executorService.shutdown();
    }
}
