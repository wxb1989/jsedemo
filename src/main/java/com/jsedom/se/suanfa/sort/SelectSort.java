package com.jsedom.se.suanfa.sort;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
            int [] array=new int[]{10,23,4,56,-8,11,13};

        System.out.println(selectSort(array).toString());
    }

    public static int[] selectSort(int [] array){
        int tmp=0;
        int k=0;
        int length = array.length;
        for (int i = 0; i < length-1 ; i++) {
            k=i;
            for (int j = i; j <length ; j++) {
                if(array[j]< array[k]){
                    k=j;
                }
            }
            tmp = array[i];
            array[i]=array[k];
            array[k]=tmp;
        }
        return array;
    }
}
