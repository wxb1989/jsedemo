package com.jsedom.se.thinkinjava.singleton;

/**
 * 单例模式排除 DCL双重锁控制 因为DCL可能会出现Singleton可能为null的情况
 *
 * 线程A                                  线程B
 *  分配内存地址
 *  设置Singleton的指针对象
 *                                      判断 Singleton是否为NULL
 *                                      线程B访问Singleton对象 （但此时该类可能未被实例化而是只像了内存中的地址坑未null）
 *  初始化Singleton对象
 *
 *
 * Created by Administrator on 2017/3/3.
 */
public class Singleton {

    private   static  Singleton singleton;

    private volatile int count=1;

    private  Singleton(){

    }

    public static  Singleton getInstance(){
        if(singleton == null ){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton =new Singleton();
                }
            }
        }
        return singleton;
    }

    public int readI(){
       return count;
    }
    public void writeI(int j){
        count=j;
    }
}

/*
    //这种方式是利用volatile在多线程中的可见性来解决 多线程中Singleton有可能出现null的情况
public class Singleton {
    private volatile  static  Singleton singleton;
     private  Singleton(){

        }

      public static  Singleton getInstance(){
        if(singleton == null ){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton =new Singleton();
                }
            }

        }
        return singleton;
    }
}




实例化的方式解决多线程中可能出现null的问题

public class Singleton {

    private Singleton() {

    }

    public static Singleton getInstance() {
        return SingletonBuilder.singleton;
    }


    private static class SingletonBuilder {
        public static Singleton singleton = new Singleton();
    }
}
*/


