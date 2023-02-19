package com.rychko.datastructures;


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

        if (index == size) {
            if (index == 0) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
            }
            tail = newNode;
        } else if (index < size) {
            Node<T> temp;
            if (index == 0) {
                temp = head;
                newNode.next = temp;
                temp.prev = newNode;
                head = newNode;
            } else {
                temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
                newNode.next = temp;
                temp.prev = newNode;
            }
        }
        size++;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> removed = findNode(index);
        if (index == 0) {
            head = removed.next;
            head.prev = null;
        } else {
            removed.prev.setNext(removed.next);
            removed.next.setPrev(removed.prev);
        }

        if (Objects.equals(removed, tail)) {
            tail = removed.prev;
            tail.next = null;
        }
        size--;
        return removed.value;

    }

    @Override
    public T get(int index) {
        return findNode(index).value;
    }

    private Node<T> findNode(int index) {
        checkIndex(index);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("your index " + index + " is out of bounds for size " + size);
        }
    }

    @Override
    public T set(T value, int index) {
        Node<T> nodeToChange = findNode(index);
        T oldValue = nodeToChange.getValue();
        nodeToChange.setValue(value);
        return oldValue;
    }

    @Override
    public void clear() {
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            temp.value = null;
            temp.prev = null;
            if (!Objects.equals(temp.next, null)) {
                temp = temp.next;
                temp.prev.setNext(null);
            }
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
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(temp.getValue(), value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> temp = tail;
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(temp.getValue(), value)) {
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
            T value = temp.getValue();
            temp = temp.getNext();
            if (value == null){
                stringJoiner.add("null");
                continue;
            }
            stringJoiner.add(value.toString());
        }
        return stringJoiner.toString();
    }


    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }


}
