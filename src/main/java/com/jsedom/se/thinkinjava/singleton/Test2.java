package com.jsedom.se.thinkinjava.singleton;

/**
 * Created by Administrator on 2017/3/3.
 */
public class Test2 {
    public static void main(String[] args) {
        new Thread( new Runnable() {
            public void run() {
                Singleton singleton =   Singleton.getInstance();
                int count =   singleton.readI();
                System.out.println( " count >>>  " +count+  " ");
            }
        }).start();
    }
}
