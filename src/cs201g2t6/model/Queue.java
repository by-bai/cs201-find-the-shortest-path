package cs201g2t6.model;

import java.util.*;
// Queue implemented by Linked Lists
public class Queue<E> {
    private LinkedList<E> list = new LinkedList<>();

    public Queue() {
        list = new LinkedList<>();
    }

    public void Add(E item) {
        list.addLast(item);
    }

    // Retrieves and removes the head (first element) of this list.
    public E poll() {
        return list.poll();
    }

    public E peek () {
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
    
}
