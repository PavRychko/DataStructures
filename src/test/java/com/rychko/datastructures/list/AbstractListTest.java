package com.rychko.datastructures.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private final String first = "first";
    private final String second = "second";
    private final String third = "third";
    private final List<String> list = getList();

    public abstract List getList();

    @BeforeEach
    public void prepareList() {
        List<String> list = this.list;
        list.add(first);
        list.add(second);
        list.add(third);
        assertEquals(3, list.size());
    }

    @AfterEach
    public void clearList() {
        list.clear();
    }


    @Test
    public void addWithoutIndexTest() {
        //verify
        assertEquals(first, list.get(0));
        assertEquals(second, list.get(1));
        assertEquals(third, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void addWithIndexTest() {
        //given
        String value = "test";

        //do
        list.add(value, 0);
        list.add(value, list.size());
        list.add(null, 3);

        //verify
        assertEquals(6, list.size());
        assertEquals(value, list.get(0));
        assertEquals(first, list.get(1));
        assertEquals(second, list.get(2));
        assertNull(list.get(3));
        assertEquals(third, list.get(4));
        assertEquals(value, list.get(5));
    }

    @Test
    public void addWithWrongIndexThrowsExceptionTest() {
        //given
        String expectedNegativeIndexMessage = "your index -1 is out of bounds for size 3";
        String expectedMorThenSizeIndexMessage = "your index 12 is out of bounds for size 3";

        //do
        Exception negativeIndexException = assertThrows(IndexOutOfBoundsException.class, () -> list.add(first, -1));
        Exception moreThenSizeException = assertThrows(IndexOutOfBoundsException.class, () -> list.add(second, 12));

        //verify
        assertEquals(3, list.size());
        assertEquals(expectedNegativeIndexMessage, negativeIndexException.getMessage());
        assertEquals(expectedMorThenSizeIndexMessage, moreThenSizeException.getMessage());
    }

    @Test
    public void removeMovesValuesBackwardTest() {
        //do
        Object removed = list.remove(0);

        //verify
        assertEquals(2, list.size());
        assertEquals(second, list.get(0));
        assertEquals(first, removed);
    }

    @Test
    public void removeWithWrongIndexThrowsExceptionTest() {
        //given
        String expectedNegativeMessage = "your index -1 is out of bounds for size 3";
        String expectedBigMessage = "your index 3 is out of bounds for size 3";

        //do
        Exception negativeException = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        Exception moreThenSizeException = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));

        //verify
        assertEquals(expectedNegativeMessage, negativeException.getMessage());
        assertEquals(expectedBigMessage, moreThenSizeException.getMessage());
        assertEquals(3, list.size());
    }

    @Test
    public void getWithWrongIndexThrowsExceptionTest() {
        //given
        String expectedNegativeMessage = "your index -1 is out of bounds for size 3";
        String expectedBigMessage = "your index 3 is out of bounds for size 3";

        //do
        Exception negativeException = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        Exception moreThenSizeException = assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));

        //verify
        assertEquals(expectedNegativeMessage, negativeException.getMessage());
        assertEquals(expectedBigMessage, moreThenSizeException.getMessage());
        assertEquals(3, list.size());
    }

    @Test
    public void setTest() {
        //given
        String newValue = "test";

        //do
        Object old = list.set(newValue, 0);

        //verify
        assertEquals(first, old);
        assertEquals(newValue, list.get(0));
        assertEquals(3, list.size());
    }

    @Test
    public void setWithWrongIndexThrowsExceptionTest() {
        //given
        String newValue = "test";
        String expectedNegativeMessage = "your index -1 is out of bounds for size 3";
        String expectedBigMessage = "your index 3 is out of bounds for size 3";

        //do
        Exception negativeException = assertThrows(IndexOutOfBoundsException.class, () -> list.set(newValue, -1));
        Exception moreThenSizeException = assertThrows(IndexOutOfBoundsException.class, () -> list.set(newValue, 3));

        //verify
        assertEquals(expectedNegativeMessage, negativeException.getMessage());
        assertEquals(expectedBigMessage, moreThenSizeException.getMessage());
        assertEquals(3, list.size());
        assertEquals(first, list.get(0));
        assertEquals(second, list.get(1));
        assertEquals(third, list.get(2));
    }

    @Test
    public void clearAndIsEmptyTest() {
        //do & verify
        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void containsTest() {
        //given
        String fourth = "four";

        //do & verify
        assertTrue(list.contains(first));
        assertTrue(list.contains(second));
        assertTrue(list.contains(third));
        assertFalse(list.contains(fourth));
    }

    @Test
    public void containsNullValueTest() {
        //do & verify
        assertFalse(list.contains(null));

        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void indexOfTest() {
        //given
        list.add(first);
        list.add(null);
        String test = "test";

        //do
        int resultForFirst = list.indexOf(first);
        int resultForThird = list.indexOf(third);
        int resultForNull = list.indexOf(null);
        int resultForNonAddedObject = list.indexOf(test);

        //verify
        assertEquals(0, resultForFirst);
        assertEquals(2, resultForThird);
        assertEquals(4, resultForNull);
        assertEquals(-1, resultForNonAddedObject);
    }

    @Test
    public void lastIndexOfTest() {
        //given
        list.add(second);
        list.add(null);
        String test = "test";

        //do
        int resultForSecond = list.lastIndexOf(second);
        int resultForThird = list.lastIndexOf(third);
        int resultForNull = list.lastIndexOf(null);
        int resultForNonAddedObject = list.lastIndexOf(test);
        int resultForFirst = list.lastIndexOf(first);

        //verify
        assertEquals(3, resultForSecond);
        assertEquals(2, resultForThird);
        assertEquals(4, resultForNull);
        assertEquals(-1, resultForNonAddedObject);
        assertEquals(0, resultForFirst);
    }

    @Test
    public void toStringTest() {
        //given
        list.add(null);
        String expected = "[first, second, third, null]";

        //do
        String result = list.toString();

        //verify
        assertEquals(expected, result);
    }

    @Test
    public void iteratorNextTest() {
        //given
        Iterator<String> iterator = list.iterator();
        int counter = 0;

        //do
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }

        //verify
        assertEquals(list.size(), counter);
    }

    @Test
    public void iteratorNextThrowsNoSuchElementExceptionTest() {
        //given
        Iterator<String> iterator = list.iterator();

        //do
        iterator.next();
        iterator.next();
        iterator.next();

        //verify
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iteratorRemoveChangesSize() {
        //given
        Iterator<String> iterator = list.iterator();

        //do
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        assertEquals(0, list.size());
    }

    @Test
    public void iteratorThrowsOnTwoTimesRemove() {
        //given
        Iterator<String> iterator = list.iterator();

        //do & verify
        assertThrows(IllegalStateException.class, iterator::remove);

        iterator.next();
        iterator.remove();

        assertThrows(IllegalStateException.class, iterator::remove);
        assertEquals(2, list.size());
    }


}