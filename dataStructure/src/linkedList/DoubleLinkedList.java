package linkedList;

import java.util.ArrayList;

public class DoubleLinkedList<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node <T> tail = null;
    private Node<T> pointer = null;
    private Node<T> elemNext = null;
    private static class Node<T>{
        T data;
        Node<T> prev, next;
        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        public Node(Node<T> node){
            this.data = node.data;
            this.prev = node.prev;
            this.next = node.next;
        }
        @Override
        public String toString(){
            return data.toString();
        }
    }
    
    public boolean isEmpty(){
        return head == null && tail == null;
    }
    
    public void addLast(T elem){
        if(isEmpty()){
            head = tail = new Node<T>(elem, null, null);
        }else {
            tail.next = new Node<>(elem, tail, null);
            tail = tail.next;
        }
        pointer = new Node<T>(head);
        ++size;
    }

    public void addTop(T elem){
        if(isEmpty()){
            head = tail = new Node<T>(elem, null, null);
            pointer = new Node<T>(head);
        }else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        pointer = new Node<T>(head);
        ++size;
    }

    public T gets(int position){
        if(size < position || position < 0){
            throw new IndexOutOfBoundsException("");
        }
        Node<T> element = new Node<T>(head);
        for(int i=0; i<size; ++i){
            if(i == position) break;
            element = element.next;
        }
        return element.data;
    }

    public T getTop(){
        if(!isEmpty())
            return head.data;
        throw new NullPointerException();
    }

    public T getBack(){
        if(!isEmpty())
            return tail.data;
        throw new NullPointerException();
    }

    public int removeAt(int position){
        if(size < position || position < 0 || isEmpty()){
            throw new IndexOutOfBoundsException("");
        }
        Node<T> element = new Node<T>(head);
        if(position == 0){
            head = head.next;
            head.prev = null;
            --size;
            pointer = new Node<T>(head);
            return 1;
        }
        for(int i=0; i<size; ++i){
           if(i == position){
               element.prev.next = element.next;
               --size;
               pointer = new Node<T>(head);
               return 1;
           }
           element = element.next;
        }
        pointer = new Node<T>(head);
        return 0;
    }

    public int removeTop(){
       if(isEmpty()){
            throw new NullPointerException();
        }
        head = head.next;
        head.prev = null;
        --size;
        pointer = new Node<T>(head);
        return 1;
    }

    public int removeBack(){
        if(isEmpty()){
            throw new NullPointerException();
        }
        tail = tail.prev;
        tail.next = null;
        --size;
        pointer = new Node<T>(head);
        return 1;
    }

    public ArrayList<T> toArrayList(){
        if(isEmpty())
            throw new NullPointerException();
        ArrayList<T> myList = new ArrayList<>();
        while (head !=null){
            myList.add(head.data);
            head = head.next;
        }
        return myList;
    }

    public boolean hasNext(){
        return this.pointer != null;
    }

    public T next(){
        elemNext = new Node<T>(pointer);
        pointer = pointer.next;
        return elemNext.data;
    }

    public boolean hasBack(){
        return tail != null;
    }

    public T back(){
        pointer = new Node<T>(tail);
        pointer = pointer.prev;
        return pointer.data;
    }

    public int indexOf(T data){
        int index = -1;
        for (int i=0; i<size; ++i){
            if(gets(i) == data){
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean contains(T data){
        return indexOf(data) != -1;
    }

    @Override
    public String toString(){
        Node<T> element = head;
        StringBuilder str = new StringBuilder();
        str.append("NULL->");
        while (element != null){
            str.append("[").append(element.data).append("]").append("->");
            element = element.next;
        }
        str.append("NULL");
        return str.toString();
    }
    //get the size of the list
    public int getSize() {
        return size;
    }

}
