package com.rychko.datastructures;

import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {

    private int size = 0;
    private T[] array;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size >= array.length) {
            increaseArrayLength();
        }

        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }

        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T old = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return old;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public T set(T element, int index) {
        checkIndex(index);
        T old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    private void increaseArrayLength() {
        int newLength = (int) (size * 1.5) + 1;
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }
}