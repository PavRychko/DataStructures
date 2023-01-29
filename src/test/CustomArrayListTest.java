package test;

import main.CustomArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {
    private final String first = "first";
    private final String second = "second";
    private final String third = "third";

    @Test
    public void addWithoutIndexTest() {
        //given
        CustomArrayList list = prepareArrayList();

        //verify
        assertEquals(first, list.get(0));
        assertEquals(second, list.get(1));
        assertEquals(third, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void addWithIndexTest() {
        //given
        CustomArrayList list = prepareArrayList();
        String value = "test";

        //do
        list.add(value, 0);
        list.add(value, list.size());

        //verify
        assertEquals(5, list.size());
        assertEquals(value, list.get(0));
        assertEquals(first, list.get(1));
        assertEquals(second, list.get(2));
        assertEquals(third, list.get(3));
        assertEquals(value, list.get(4));
    }

    @Test
    public void addWithWrongIndexThrowsExceptionTest() {
        //given
        CustomArrayList list = new CustomArrayList();

        String expectedNegativeMessage = "your index -1 is out of bounds for size 0";
        String expectedBigMessage = "your index 1 is out of bounds for size 0";

        //do
        Exception negativeIndexException = assertThrows(IndexOutOfBoundsException.class, () -> list.add(first, -1));
        Exception moreThenSizeException = assertThrows(IndexOutOfBoundsException.class, () -> list.add(second, 1));

        //verify
        assertEquals(0, list.size());
        assertEquals(expectedNegativeMessage, negativeIndexException.getMessage());
        assertEquals(expectedBigMessage, moreThenSizeException.getMessage());
    }

    @Test
    public void removeMovesValuesBackwardTest() {
        //given
        CustomArrayList list = prepareArrayList();

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
        CustomArrayList list = prepareArrayList();

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
        CustomArrayList list = prepareArrayList();
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
        CustomArrayList list = prepareArrayList();
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
        CustomArrayList list = prepareArrayList();
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
        //given
        CustomArrayList list = prepareArrayList();

        //do & verify
        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void containsTest() {
        //given
        CustomArrayList list = prepareArrayList();
        String fourth = "four";

        //do & verify
        assertTrue(list.contains(first));
        assertTrue(list.contains(second));
        assertTrue(list.contains(third));
        assertFalse(list.contains(fourth));
    }

    @Test
    public void containsNullValueTest() {
        //given
        CustomArrayList list = prepareArrayList();

        //do & verify
        assertTrue(list.contains(first));
        assertTrue(list.contains(second));
        assertTrue(list.contains(third));
        assertFalse(list.contains(null));

        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void indexOfTest() {
        //given
        CustomArrayList list = prepareArrayList();
        list.add(first);
        String test = "test";

        //do
        int resultForFirst = list.indexOf(first);
        int resultForThird = list.indexOf(third);
        int resultForNull = list.indexOf(null);
        int resultForNonAddedObject = list.indexOf(test);

        //verify
        assertEquals(0, resultForFirst);
        assertEquals(2, resultForThird);
        assertEquals(-1, resultForNull);
        assertEquals(-1, resultForNonAddedObject);
    }

    @Test
    public void lastIndexOfTest() {
        //given
        CustomArrayList list = prepareArrayList();
        list.add(first);
        String test = "test";

        //do
        int resultForFirst = list.lastIndexOf(first);
        int resultForThird = list.lastIndexOf(third);
        int resultForNull = list.lastIndexOf(null);
        int resultForNonAddedObject = list.lastIndexOf(test);

        //verify
        assertEquals(3, resultForFirst);
        assertEquals(2, resultForThird);
        assertEquals(-1, resultForNull);
        assertEquals(-1, resultForNonAddedObject);
    }

    @Test
    public void toStringTest() {
        //given
        CustomArrayList list = prepareArrayList();
        String expected = "[first, second, third]";

        //do
        String result = list.toString();

        //verify
        assertEquals(expected, result);
    }

    private CustomArrayList prepareArrayList() {
        CustomArrayList list = new CustomArrayList();
        list.add(first);
        list.add(second);
        list.add(third);
        assertEquals(3, list.size());
        return list;
    }
}