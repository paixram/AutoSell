/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import espol.edu.ec.autosell.utils.Malloc;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Luizzz
 */
public class MallocTest {
    private Malloc<Integer> malloc;

    @Before
    public void setUp() {
        malloc = new Malloc<Integer>(3);
    }
    
    @Test
    public void testAdd() {
        assertTrue(malloc.add(1));
        assertTrue(malloc.add(2));
        assertEquals(10, malloc.size());
    }
    
    @Test
    public void testGet() {
        malloc.add(1);
        malloc.add(2);
        assertEquals(Integer.valueOf(1), malloc.get(0));
        assertEquals(Integer.valueOf(2), malloc.get(1));
    }
    
    @Test
    public void testRemove() {
        malloc.add(1);
        malloc.add(2);
        assertEquals(Integer.valueOf(1), malloc.remove(0));
        assertEquals(10, malloc.size());
        assertEquals(Integer.valueOf(2), malloc.get(0));
    }
    
    @Test
    public void testIterator() {
        malloc.add(1);
        malloc.add(2);
        malloc.add(3);
        Iterator<Integer> iterator = malloc.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testForEach() {
        malloc.add(1);
        malloc.add(2);
        malloc.add(3);
        StringBuilder result = new StringBuilder();
        malloc.forEach(result::append);
        assertEquals("123", result.toString());
    }

    @Test
    public void testIndexOf() {
        malloc.add(1);
        malloc.add(2);
        malloc.add(3);
        assertEquals(2, malloc.indexOf(3));
    }
    
    @Test
    public void testAddAll() {
        Malloc<Integer> sourceList = new Malloc<>(3);
        sourceList.add(1);
        sourceList.add(2);
        sourceList.add(3);
        System.out.println("SIze source malloc: " + sourceList.size());
       
        malloc.addAll(sourceList);

        assertEquals(3, malloc.size()); // Verifica que se hayan agregado todos los elementos
        //assertTrue(malloc.contains(1)); // Verifica que se haya agregado el elemento 1
        //assertTrue(malloc.contains(2)); // Verifica que se haya agregado el elemento 2
        //assertTrue(malloc.contains(3)); // Verifica que se haya agregado el elemento 3
        assertEquals(0, malloc.indexOf(1));
        assertEquals(1, malloc.indexOf(2));
        assertEquals(2, malloc.indexOf(3));
    }
}
