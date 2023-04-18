package com.rychko.datastructures.map;

public class HashMapTest<K, V> extends AbstractMapTest<K, V> {

    @Override
    public Map<String, String> getMap() {
        return new HashMap<String, String>();
    }
}
