package com.rychko.datastructures.map;


import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
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
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            entry = (Entry) iterator.next();
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
    public Iterator<Entry> iterator() {
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
            findNotEmptyBucket();
            int bucketSize = buckets[bucketNumber].size();
            return ( bucketSize > entryNumber) && bucketNumber < buckets.length;
        }

        @Override
        public Entry next() {
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

        public boolean isEmptyBucket(List<Entry> bucket) {
            return bucket.size() == 0;
        }

        public void findNotEmptyBucket() {
            if (bucketNumber < buckets.length - 1 && isEmptyBucket(buckets[bucketNumber])) {
                bucketNumber++;
                findNotEmptyBucket();
            }
        }
    }
}


