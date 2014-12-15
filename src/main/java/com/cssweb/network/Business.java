package com.cssweb.network;

import com.cssweb.network.BusinessThread;
import com.cssweb.network.CustomMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chenhf on 14-2-21.
 */
public class Business {

    private BlockingQueue<CustomMessage> queue;
    private ExecutorService threadpool;
    private int threadpoolSize;

    public Business() {
        threadpoolSize = 10;
    }

    public BlockingQueue<CustomMessage> getQueue() {
        return queue;
    }

    public void start() {
        queue = new LinkedBlockingDeque<CustomMessage>();

        threadpool = Executors.newFixedThreadPool(threadpoolSize);

        for (int i = 0; i < threadpoolSize; i++) {
            BusinessThread businessThread = new BusinessThread(queue);
            threadpool.execute(businessThread);
        }
    }

    public void stop() {

    }

}
