package com.company;
import linkedList.DoubleLinkedList;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	    DoubleLinkedList<Integer> myList = new DoubleLinkedList<>();
        myList.addTop(25);
        myList.addLast(75);
        myList.addTop(14);
        myList.addTop(103);
        myList.addLast(8);
        myList.addTop(45);
        myList.addLast(91);
        System.out.println(myList.toString());
        myList.removeAt(4);
        myList.removeBack();
        myList.removeTop();
        //PROBLEME D'ITERATION AVEC NEXT ET HASNEXT
        while (myList.hasNext()){
            System.out.println(myList.next());
        }

        System.out.println(myList.toString());
    }
}
