package espol.edu.ec.autosell.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Malloc<E> implements List<E>, Iterable<E> {
    
    // Presets
    private static final int DEFAULT_SIZE = 5;
    
    // Object for handle
    private E[] __DATA_BLOCK__;
    private int len;
    private int cap;
    
    public Malloc(int size) {
        __DATA_BLOCK__ = (E[]) new Object[size];
        len = 0;
        cap = size;
    }
    
    public Malloc() {
        __DATA_BLOCK__ = (E[]) new Object[DEFAULT_SIZE];
        len = 0;
        cap = DEFAULT_SIZE;
    }
    
    @Override
    public int size() {
        return len;
    }

    @Override
    public boolean isEmpty() {  
        return len == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < len; i++) {
            if (__DATA_BLOCK__[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        E[] array = (E[]) new Object[len];
        System.arraycopy(__DATA_BLOCK__, 0, array, 0, len);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        /*if (a.length < len) {
            return (T[]) System.arraycopy(__DATA_BLOCK__, 0, (T[]) new Object[len], 0, len);
        }
        System.arraycopy(__DATA_BLOCK__, 0, a, 0, len);
        if (a.length > len) {
            a[len] = null;
        }
        return a;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(E e) {
        reajustar();
        __DATA_BLOCK__[len++] = e;
        return true;
    }
    
    private void reajustar() {
        if (len >= cap) {
            AumentarCapacidad(cap * 2);
        }
    }
    
    private void AumentarCapacidad(int newCap) {
        E[] newCustomArray = (E[]) new Object[newCap];
        System.arraycopy(__DATA_BLOCK__, 0, newCustomArray, 0, len);
        __DATA_BLOCK__ = newCustomArray;
        cap = newCap;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        for (int i = 0; i < len; i++) {
            __DATA_BLOCK__[i] = null;
        }
        len = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + len);
        }
        return __DATA_BLOCK__[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + len);
        }
        E oldValue = __DATA_BLOCK__[index];
        __DATA_BLOCK__[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > len) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + len);
        }
        reajustar();
        System.arraycopy(__DATA_BLOCK__, index, __DATA_BLOCK__, index + 1, len - index);
        __DATA_BLOCK__[index] = element;
        len++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + len);
        }
        E removedElement = __DATA_BLOCK__[index];
        int numMoved = len - index - 1;
        if (numMoved > 0) {
            System.arraycopy(__DATA_BLOCK__, index + 1, __DATA_BLOCK__, index, numMoved);
        }
        __DATA_BLOCK__[--len] = null;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < len; i++) {
            if (__DATA_BLOCK__[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = len - 1; i >= 0; i--) {
            if (__DATA_BLOCK__[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<E> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action);
    }
    
    @Override
    public Iterator<E> iterator() {
        return new MallocIterator();
    }
    
    public class MallocIterator implements Iterator<E> {
        private int currentIndex = 0;
        
        public MallocIterator() {
        }

        @Override
        public boolean hasNext() {
            return currentIndex < len;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return __DATA_BLOCK__[currentIndex++];
        }
    }
    
    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < len; i++) {
            sb.append(__DATA_BLOCK__[i]);
            if (i < len - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}