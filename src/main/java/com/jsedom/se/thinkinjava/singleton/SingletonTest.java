package com.jsedom.se.thinkinjava.singleton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/3/3.
 */
public class SingletonTest {
    private static final  int TASK_SIZE = 10;
    static ExecutorService executorService =null;

    public static void main(String[] args) {
//        executorService = Executors.newFixedThreadPool(TASK_SIZE);
//        Date date1 = new Date();
//        // 创建多个有返回值的任务
//        List<Future> list = new ArrayList<Future>();
//        for (int i = 0; i < 8; i++) {
//            Callable c = new MyCallable(i + " ");
//            // 执行任务并获取Future对象
//            Future f = executorService.submit(c);
//            //System.out.println(">>>" + f.get().toString());
//            list.add(f);
//        }
//
//        // 关闭线程池
//        executorService.shutdown();
//
//        for (Future f : list) {
//            System.out.println(">>>" + f.isDone());
//        }
//
//        Date date2 = new Date();
//        System.out.println("----程序结束运行----，程序运行时间【"
//                + (date2.getTime() - date1.getTime()) + "毫秒】");


       new Thread( new Runnable() {
            public void run() {
                Singleton singleton =   Singleton.getInstance();
                singleton.writeI(10);
                String name =singleton.getClass().getName();
                System.out.println(name + " >>>  " +Thread.currentThread().getName()+  "  任务启动");
            }
        }).start();
    }
}
//
//class MyCallable implements Callable<Object> {
//    private String taskNum;
//
//    MyCallable(String taskNum) {
//        this.taskNum = taskNum;
//    }
//
//    public Object call() throws Exception {
//        Singleton singleton =   Singleton.getInstance();
//        String name =singleton.getClass().getName();
//        System.out.println(name + " >>>  " + taskNum +  "  任务启动");
//        Date dateTmp1 = new Date();
//        Thread.sleep(1000);
//        Date dateTmp2 = new Date();
//        long time = dateTmp2.getTime() - dateTmp1.getTime();
//        System.out.println(">>>" + taskNum + "任务终止");
//        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
//    }
//}
