package com.jsedom.rabbitmq;

/**
 * Created by Administrator on 2017/2/28.
 */
public class QueueConsumerTest {
    public static void main(String[] args) throws Exception {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
