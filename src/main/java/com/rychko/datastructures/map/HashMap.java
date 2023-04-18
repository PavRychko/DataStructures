package com.rychko.datastructures.map;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V>, Iterable {
    private int size = 0;
    private double loadFactor;
    private ArrayList<Entry>[] buckets = new ArrayList[5];

    public HashMap() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V get(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            return entry.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @Override
    public V put(K key, V value) {
        Entry entry = getEntry(key);
        ArrayList<Entry> list = getBucket(key);
        size++;
        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
        entry = new Entry(key, value);
        list.add(entry);
        return null;
    }


    @Override
    public V remove(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            ArrayList<Entry> list = getBucket(key);
            size--;
            V result = entry.value;
            list.remove(entry);
            return result;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private Entry getEntry(K key) {
        ArrayList<Entry> list = getBucket(key);
        Entry entry = null;
        for (int i = 0; i < list.size(); i++) {
            entry = list.get(i);
            if (Objects.equals(key, entry.key)) {
                return entry;
            }
        }
        return null;
    }

    private ArrayList<Entry> getBucket(K key) {
        return buckets[calculateBucket(key)];
    }

    private int calculateBucket(K key) {
        if (key == null) {
            throw new NullPointerException("null key is not allowed");
        }
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public java.util.Iterator iterator() {
        return new MapIterator();
    }

    private class Entry {
        private K key;
        private V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }


    }

    private class MapIterator implements Iterator {
        private int bucketNumber = 0;
        private int entryNumber = 0;

        @Override
        public boolean hasNext() {
            return !(buckets[bucketNumber].size() > entryNumber) && bucketNumber < buckets.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Entry result = buckets[bucketNumber].get(entryNumber);
                entryNumber++;
                if (buckets[bucketNumber].size() == entryNumber) {
                    bucketNumber++;
                    entryNumber = 0;
                }
                return result;
            }
            throw new NoSuchElementException();
        }
    }
}


