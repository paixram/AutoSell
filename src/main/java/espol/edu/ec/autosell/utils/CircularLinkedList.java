/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.utils;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 *
 * @author José Miguel
 */

public class CircularLinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private Node<E> current;

    private int size;

    public static class Node<E> {
        public E element;
        public Node<E> next;
        public Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    public CircularLinkedList() {
        head = null;
        tail = null;
        current = null;
        size = 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = tail;
            head.prev = tail;
            tail.next = head;
            tail.prev = head;
            current = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }
        size++;
    }

    public E next() {
        if (current != null) {
            current = current.next;
            return current.element;
        }
        return null;
    }

    public E prev() {
        if (current != null) {
            current = current.prev;
            return current.element;
        }
        return null;
    }

    public E getCurrent() {
        if (current != null) {
            return current.element;
        }
        return null;
    }

    public int size() {
        return size;
    }
    public void sort(Comparator<E> comparator) {
        if (size <= 1) {
            return;
        }
        Node<E> current = head;
        do {
            Node<E> innerCurrent = current.next;
            while (innerCurrent != head) {
                if (comparator.compare(innerCurrent.element, current.element) < 0) {
                    E temp = current.element;
                    current.element = innerCurrent.element;
                    innerCurrent.element = temp;
                }
                innerCurrent = innerCurrent.next;
            }
            current = current.next;
        } while (current != head);
    }

    public void remove(E element) {
        if (size == 0) return;

        Node<E> current = head;
        do {
            if (current.element.equals(element)) {
                if (size == 1) {
                    head = null;
                    tail = null;
                    current = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    if (current == head) {
                        head = current.next;
                    }
                    if (current == tail) {
                        tail = current.prev;
                    }
                }
                size--;
                return;
            }
            current = current.next;
        } while (current != head);
    }
    public Node<E> getHead() {
    return head;
}


    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private boolean firstIteration = true;

            @Override
            public boolean hasNext() {
                return current != null && (firstIteration || current != head);
            }

            @Override
            public E next() {
                if (current == null || (!firstIteration && current == head)) {
                    throw new java.util.NoSuchElementException();
                }
                E element = current.element;
                current = current.next;
                firstIteration = false;
                return element;
            }
        };
    }
}




