package com.rychko.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayListTest extends AbstractListTest {

    @Override
    public List<String> getList() {
        return new ArrayList<String>();
    }

    @Test
    public void increaseCapacityTest() {
        //given
        ArrayList<String> list = new ArrayList<>(10);

        //do
        for (int i = 0; i < 100; i++) {
            list.add(Integer.toString(i));
        }

        //verify
        assertEquals(100, list.size());
    }
}
