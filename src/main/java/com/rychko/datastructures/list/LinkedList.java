package com.rychko.datastructures.list;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> temp = findNode(index);
            newNode.next = temp;
            newNode.prev = temp.prev;
            temp.prev.next = newNode;
            temp.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> removed = findNode(index);

        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head = removed.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = removed.prev;
            tail.next = null;
        } else {
            removed.prev.next = removed.next;
            removed.next.prev = removed.prev;
        }

        size--;
        return removed.value;

    }

    @Override
    public T get(int index) {
        return findNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        Node<T> nodeToChange = findNode(index);
        T oldValue = nodeToChange.value;
        nodeToChange.value = value;
        return oldValue;
    }

    @Override
    public void clear() {
        head = tail = null;
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
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(temp.value, value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> temp = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(temp.value, value)) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            T value = temp.value;
            stringJoiner.add(String.valueOf(value));
            temp = temp.next;
        }
        return stringJoiner.toString();
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }

    private Node<T> findNode(int index) {
        checkIndex(index);
        Node<T> node;
        if (index > size / 2) {
            node = tail;
            for (int i = 0; i < size - index - 1; i++) {
                node = node.prev;
            }
        } else {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }

        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> current = head;
            boolean flag = false;
            int position = -1;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    position++;
                    T result = current.value;
                    current = current.next;
                    flag = true;
                    return result;
                }

                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if(flag) {
                    LinkedList.this.remove(position);
                    position--;
                    flag = false;
                } else {
                    throw new IllegalStateException("element doesn`t exist, you should call next() method before remove");
                }
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }


}
