package com.jsedom.se.thinkinjava.observerpattern;

import java.util.Observable;

/**
 * 作者类，要继承自被观察者类
 * Created by Administrator on 2017/6/23.
 */
public class Writer extends Observable {

    private String name;//作者的名称

    private String lastNovel;//记录作者最新发布的小说

    public Writer(String name) {
        super();
        this.name = name;
        WriterManager.getInstance().add(this);
    }

    //作者发布新小说了，要通知所有关注自己的读者
    public void addNovel(String novel) {
        System.out.println(name + "发布了新书《" + novel + "》！");
        lastNovel = novel;
        setChanged();//发生改变
        notifyObservers();//通知观察者
    }

    public String getLastNovel() {
        return lastNovel;
    }

    public String getName() {
        return name;
    }
}
