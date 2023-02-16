package com.oopsmails.springboot.mockbackend.multiplecalls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class ApiMultipleCallsService {
    private static int THREAD_POLL_SIZE = 3;

    @Autowired
    private ApiClientSimple apiClientSimple;

    public List<String> callApis(List<String> urls) throws InterruptedException {

//        ExecutorService executor = Executors.newFixedThreadPool(urls.size());
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POLL_SIZE);
        List<Callable<String>> callables = new ArrayList<>();

        for (String url : urls) {
            callables.add(() -> {
                try {
                    return apiClientSimple.callApi(url);
                } catch (Exception e) {
                    return e.getMessage();
                }
            });
        }

        List<Future<String>> futures = executor.invokeAll(callables);

        List<String> results = new ArrayList<>();

        for (Future<String> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                results.add(e.getMessage());
            }
        }

        // Shut down the executor service
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return results;
    }

}
