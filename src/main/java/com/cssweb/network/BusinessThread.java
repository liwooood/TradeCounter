package com.cssweb.network;

import com.cssweb.network.CustomMessage;

import java.util.concurrent.BlockingQueue;

/**
 * Created by chenhf on 14-2-21.
 */
public class BusinessThread implements Runnable {
    private BlockingQueue<CustomMessage> queue;
    private boolean isRunning;

    public BusinessThread(BlockingQueue<CustomMessage> queue) {
        this.queue = queue;
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            // 循环从消息队列中获取消息
        }

    }

    public void stop() {
        isRunning = false;
    }
}
