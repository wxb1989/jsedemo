package com.jsedom.se.dataalgorithms.array;

import java.util.Arrays;

/**
 * 自己实现数组
 */
public class MyArray {

    private long [] array;//存放数据的数组

    private int elements=0;//数组的元素
    private  static final  int INIT_SIZE=50;

    public MyArray() {
        this.array=new long[INIT_SIZE];
    }

    public MyArray(int maxSize) {
        this.array=new long[maxSize];
    }


    /**
     * 添加数据
     */

    public void insert(long value){
        array[elements]=value;
        elements++;
    }



    /**
     * 删除数据
     * 直接吧index后面的数字往前移一位最后吧数组的位置缩小一位
     */
    public void remove(int index){
        int length=array.length;
        if(index >= 0 && index < length) {
            array[index+1] = array[length-1];

            //数组缩容
            array = Arrays.copyOf(array, array.length-1);
            elements=length-1;
        }else{
            throw new ArrayIndexOutOfBoundsException("array index  out of bound !!");
        }
    }
    public void update(int index , int newValue){
        if(index >= 0 && index < elements){
            array[index]=newValue;
        }else{
            throw new ArrayIndexOutOfBoundsException("array index  out of bound !!");
        }
    }


    /**
     * 输出数据
     */
    public void display(){
        StringBuffer sbf = new StringBuffer("[ \n ");
        for (int i = 0; i < elements ; i++) {
            sbf.append("array index "+ i  + " value " +array[i] +" \n");
        }
        sbf.append(" \n ]");
        System.out.println(sbf.toString());
    }
}
