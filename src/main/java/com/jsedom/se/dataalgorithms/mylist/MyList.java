package com.jsedom.se.dataalgorithms.mylist;

import java.util.ArrayList;

/**
 * 自己实现普通的List
 */
public class MyList<Key> {


    private Object [] array;//存放数据的数组

    private int INIT_SIZE=10;

    public MyList() {
        this.array = new Object [INIT_SIZE];
    }

    public MyList(int initSize) {
        this.INIT_SIZE=initSize;
        this.array = new Object [INIT_SIZE];
    }

    public void add( Key key){
    }

}
