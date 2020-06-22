package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static ExecutorService service = Executors.newFixedThreadPool(3);

    public static ExecutorService getService() {
        return service;
    }

    public static void setService(ExecutorService service) {
        ThreadManager.service = service;
    }
}
