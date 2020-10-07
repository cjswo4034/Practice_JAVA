package dataStructure;

import java.util.ArrayList;
import java.util.Map;

public class Hash<K, V> {
    private int cnt, size;
    private ArrayList<Map.Entry<K, V>>[] entries;

    Hash (int size){
        this.cnt = 0;
        this.size = size;
        this.entries = new ArrayList[size];
    }

    Hash (){
        this.cnt = 0;
        this.size = 36;
        this.entries = new ArrayList[size];
    }

    public void put(K key, V value){
        int index = getIndex(key);

        ArrayList<Map.Entry<K, V>> entryList = entries[index];
        if (entryList == null) {
            entryList = new ArrayList<>();
            entries[index] = entryList;
        }

        Map.Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            entryList.add(new Entry<K, V>(key ,value));
            cnt++;
        } else entry.setValue(value);
    }

    public Map.Entry<K, V> getEntry(K key){
        int index = getIndex(key);
        if (entries[index] != null && entries[index].size() > 0){
            for (Map.Entry<K, V> entry : entries[index]){
                if (entry.getKey().equals(key)) return entry;
            }
        }
        return null;
    }

    public void remove(K key){
        int index = getIndex(key);
        Map.Entry<K, V> target = getEntry(key);
        if (target != null){
            for (Map.Entry<K, V> entry : entries[index]){
                if (entry.equals(target)){
                    entries[index].remove(target);
                    cnt--;
                    return;
                }
            }
        }
    }

    public V get(K key){
        Map.Entry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    public int getCnt(){
        return this.cnt;
    }

    public ArrayList<Map.Entry<K, V>> getEntries (K key){
        return entries[getIndex(key)];
    }

    private int getIndex(K key){
        return key.hashCode() % size;
    }

    static class Entry<K, V> implements Map.Entry<K, V>{
        final K key;
        V value;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            Entry<K, V> entry = (Entry<K, V>)obj;
            return key.equals(entry.getKey()) && value.equals(entry.getValue());
        }
    }
}
