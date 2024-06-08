/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.utils;
import java.util.LinkedList;
/**
 *
 * @author José Miguel
 */
public class CircularLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private Node<E> current;
    private int size;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

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
}

