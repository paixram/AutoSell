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
public class CircularList<E> {
    private LinkedList<E> list;
    private int currentIndex;

    public CircularList() {
        list = new LinkedList<>();
        currentIndex = -1;
    }

    public void add(E element) {
        list.add(element);
        if (currentIndex == -1) {
            currentIndex = 0;
        }
    }

    public E getCurrent() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(currentIndex);
    }

    public E getNext() {
        if (list.isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex + 1) % list.size();
        return list.get(currentIndex);
    }

    public E getPrevious() {
        if (list.isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex - 1 + list.size()) % list.size();
        return list.get(currentIndex);
    }
    public int size() {
        return list.size();
    }
}

