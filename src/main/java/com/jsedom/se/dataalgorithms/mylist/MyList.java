package com.jsedom.se.dataalgorithms.mylist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 自己实现普通的List（有点问题）
 */
public class MyList<Key> {


    private Object [] elementData;//存放数据的数组

    private int INIT_SIZE=10;
    private int size;

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
            size=elementData.length;
            return true;
        }
        if(elementData.length==INIT_SIZE){
            elementData = Arrays.copyOf(elementData,INIT_SIZE*2);
            elementData[currentIdx+1]=key;
            size=elementData.length;
            return true;
        }
        return false;
    }

    public boolean remove(int index){
        if(index > size){
            throw new IndexOutOfBoundsException();
        }

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
        return false;
    }


    public int size() {
        return size;
    }
    public Key get(int index){
        return (Key) elementData[index];
    }

    public boolean isEmpty() {
        return elementData.length == 0?true:false;
    }


    public static void main(String[] args) {
        MyList<String>myList = new MyList<String>(20);
        myList.add("aaaa");
        myList.add("bbbb");
        myList.add("cccc");
        myList.add("dddd");
        myList.add("eeee");
        myList.add("ffff");
        myList.add("gggg");
        myList.add("hhhh");

        for (int i = 0; i < myList.size(); i++) {
            String a  =myList.get(i);
            System.out.println(a);
        }

    }
}
