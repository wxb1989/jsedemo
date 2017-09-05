package com.jsedom.se.thinkinjava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/8.
 */
public class ConnectionProxy implements InvocationHandler {

    private Connection connection;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Connection.class.isAssignableFrom(proxy.getClass()) ){
            
            return null;
        }
        return method.invoke(connection,args);
    }


}
