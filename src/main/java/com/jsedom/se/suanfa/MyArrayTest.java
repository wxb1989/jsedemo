package com.jsedom.se.suanfa;

import com.jsedom.se.dataalgorithms.array.MyArray;

public class MyArrayTest {


    public static void main(String[] args) {
        MyArray myArray = new MyArray(10);
        myArray.insert(12);
        myArray.insert(10);
        myArray.insert(9);
        myArray.insert(70);
        myArray.insert(30);


//        myArray.remove(10);

        myArray.update(1,100);
        myArray.display();

    }
}
