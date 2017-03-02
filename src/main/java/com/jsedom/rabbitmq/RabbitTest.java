package com.jsedom.rabbitmq;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/28.
 */
public class RabbitTest {
    public RabbitTest() throws IOException {
//        QueueConsumer consumer = new QueueConsumer("queue");
//        Thread consumerThread = new Thread(consumer);
//        consumerThread.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }

    }
    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception{
        new RabbitTest();
    }
}
