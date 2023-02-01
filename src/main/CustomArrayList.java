package main;

import java.util.Objects;
import java.util.StringJoiner;

public class CustomArrayList implements List {

    private int size = 0;
    private int arrayCapacity;
    private Object[] array;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public CustomArrayList() {
        initiateArray(DEFAULT_INITIAL_CAPACITY);
    }

    public CustomArrayList(int capacity) {
        initiateArray(capacity);
    }

    private void initiateArray(int capacity) {
        array = new Object[capacity];
        arrayCapacity = capacity;
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (size >= arrayCapacity) {
            increaseArrayLength();
        }

        if (index == size) {
            array[size] = value;
        } else if (index < size && index >= 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        } else {
            checkIndex(index);
        }

        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object old = array[index];
        array[index] = null;
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return old;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public Object set(Object element, int index) {
        checkIndex(index);
        Object old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public void clear() {
        clear(array);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "[");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(array[i].toString());
        }
        return stringJoiner.toString();
    }

    private void increaseArrayLength() {
        int oldLength = array.length;
        int newLength = (int) (oldLength * 1.5);
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, oldLength);
        array = newArray;
        arrayCapacity = newLength;
    }

    private void clear(Object[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }
}