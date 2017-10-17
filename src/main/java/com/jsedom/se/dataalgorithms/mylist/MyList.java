package com.jsedom.se.dataalgorithms.mylist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 自己实现普通的List
 */
public class MyList<Key> {


    private Object [] elementData;//存放数据的数组

    private int INIT_SIZE=10;

    public MyList() {
        this.elementData = new Object [INIT_SIZE];
    }

    public MyList(int initSize) {
        this.INIT_SIZE=initSize;
        this.elementData = new Object [INIT_SIZE];
    }

    public boolean add( Key key){
        int currentIdx = elementData.length;
        if(currentIdx ==0){
            elementData[0]=key;
            return true;
        }
        if(elementData.length==INIT_SIZE){
            elementData = Arrays.copyOf(elementData,INIT_SIZE*2);
            elementData[currentIdx+1]=key;
            return true;
        }
        return false;
    }

    public boolean remove(int index){
        if(index >= elementData.length){
            throw new IndexOutOfBoundsException();
        }

        return false;
    }


    public int size() {
        return elementData.length;
    }

    public boolean isEmpty() {
        return elementData.length == 0?true:false;
    }

}
