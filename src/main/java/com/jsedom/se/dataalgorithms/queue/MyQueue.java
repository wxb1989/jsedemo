package com.jsedom.se.dataalgorithms.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 简单的实现了一个队列
 * @param <Item>
 */
public class MyQueue<Item> implements Iterable<Item>{

    private int N;
    // 第一个节点
    private Node first;

    // 最后一个节点
    private Node last;

    private class Node {
        private Item item;

        private Node next;
    }


    public MyQueue() {
        N = 0;
        first=null;
        last=first;
    }


    /**
     * 入队 新的Node就是last
     * @param item
     */
    public void enqueue(Item item) {
        Node oldLast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()) { // 空的時候 只有只有一个几点 first = list
            first=last;
        } else {
            oldLast.next=last;
        }
        N++;
    }

    /**
     * 出队
     * @return
     */
    public Item dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    public boolean isEmpty() {
        if(first==null){
            return true;
        }
        return false;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current=first;

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
        MyQueue<String> queue = new MyQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
