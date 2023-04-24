package com.rychko.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractMapTest<K, V> {
    private final String key1 = "test1";
    private final String value1 = "value";
    private final String key2 = "test2";
    private final String value2 = "someValue";
    private Map<String, String> map = getMap();

    public abstract Map<String, String> getMap();

    @BeforeEach
    public void fillMap() {
        map.put(key1, value1);
        map.put(key2, value2);

        assertEquals(2, map.size());
    }

    @Test
    public void putRewriteValueWithSimilarKeyTest() {
        assertEquals(value1, map.get(key1));

        map.put(key1, value2);

        assertEquals(value2, map.get(key1));
    }

    @Test
    public void putThrowsNullPointerWithNullKey() {
        assertThrows(NullPointerException.class, () -> map.put(null, value1));
    }

    @Test
    public void getTest() {
        assertEquals(value2, map.get(key2));
        assertEquals(value1, map.get(key1));
    }

    @Test
    public void getThrowsNullPointerWithNullKey() {
        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    public void containsKeyTest() {
        assertTrue(map.containsKey(key1));
        assertTrue(map.containsKey(key2));
        assertFalse(map.containsKey("someKey"));
        assertThrows(NullPointerException.class, () -> map.containsKey(null));
    }

    @Test
    public void removeTest() {
        String result = map.remove(key1);
        assertEquals(1, map.size());
        assertEquals(value1, result);

        assertNull(map.remove(key1));
    }

    @Test
    public void removeThrowsNullPointerWithNullKey() {
        assertThrows(NullPointerException.class, () -> map.remove(null));
    }

    @Test
    public void iteratorNextTest() {
        //given
        Iterator iterator = map.iterator();
        int counter = 0;

        //do
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }

        //verify
        assertEquals(map.size(), counter);
    }

    @Test
    public void iteratorNextThrowsNoSuchElementExceptionTest() {
        //given
        Iterator iterator = map.iterator();

        //do
        iterator.next();
        iterator.next();

        //verify
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }


}
