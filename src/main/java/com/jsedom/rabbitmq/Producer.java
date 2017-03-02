package com.jsedom.rabbitmq;

import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.lang.SerializationUtils;

/**
 * 功能概要：消息生产者
 * Created by Administrator on 2017/2/28.
 */
public class Producer  extends EndPoint{

    public Producer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
