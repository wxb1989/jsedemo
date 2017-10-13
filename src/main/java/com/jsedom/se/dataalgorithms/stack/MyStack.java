package com.jsedom.se.dataalgorithms.stack;

import java.util.Iterator;

/**
 * 简单的实现了一个Stack
 * @param <Item>
 */
public class MyStack <Item> implements Iterable<Item> {

    private int N;//容量

    private Node head;
     class Node{
        private Item item;

        private Node next;
    }


    public MyStack() {
        this.N=0;
        this.head = new Node();
    }

    public void push(Item item){
        Node first=new Node();
        first.item=item;
        first.next=head.next;
        head.next=first;
        N++;
    }

    public Item  pop(){
        Node first=head.next;
        head.next=first.next;
        N--;
        return first.item;
    }

    public int size(){
         return N;
    }

    public boolean isEmpty() {
        if(N == 0) {
            return true;
        }
        return false;
    }

    @Override
    public  Iterator<Item> iterator() {
        return new ListIterator();
    }

   private class ListIterator implements  Iterator<Item>{

       private Node current=head.next;

       @Override
       public boolean hasNext() {
           return current != null;
       }

       @Override
       public Item next() {
           Item item=current.item;
           current=current.next;
           return item;
       }

       @Override
       public void remove() {
           throw new UnsupportedOperationException();
       }
   }

    public static void main(String[] args) {
        MyStack<String> stack =new MyStack<String>();
        stack.push("aaa");
        stack.push("acb");
        stack.push("aca");

        System.out.println(stack.size());

        stack.push("aaa");
        Iterator<String> iterator=stack.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
