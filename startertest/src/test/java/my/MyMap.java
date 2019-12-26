package my;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2019/12/25.
 */
public class MyMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    private Entry2<K, V>[] table;
    private float loadFactor = 0.75f;
    private int capacity = 16;
    private int resizeThreshold;
    private int hashMod;
    private int count;

    public MyMap() {
        table = createTable(capacity);
        updateConstant();
    }

    private Entry2<K, V>[] createTable(int capacity) {
        return (Entry2<K, V>[]) (new Entry2[capacity]);
    }

    private void updateConstant() {
        capacity = table.length;
//        resizeThreshold = (int) (capacity * loadFactor);
        resizeThreshold = capacity * 3 / 4;
        hashMod = capacity - 1;
    }

    private int hash(Object key) {
        return key.hashCode() & hashMod;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int h = hash(key);
        for (Entry2 p = table[h]; p != null; p = p.next) {
            if (p.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int h = hash(key);
        for (Entry2 p = table[h]; p != null; p = p.next) {
            if (p.key.equals(key)) {
                return (V) p.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int h = hash(key);
        V oldValue = null;
        Entry2 p;
        for (p = table[h]; p != null; p = p.next) {
            if (p.key.equals(key)) {
                oldValue = (V) p.value;
                p.value = value;
                break;
            }
        }
        if (p == null) {
            table[h] = new Entry2(key, value, table[h]);
        }
        count++;
        if (size() > resizeThreshold) {
            resize();
        }
        return oldValue;
    }

    private void resize() {
        Entry2<K, V>[] table2 = createTable(capacity << 1);
        for (int i = 0; i < capacity; i++) {
            for (Entry2 p = table[i]; p != null; ) {
                Entry2 next = p.next;

                int h = p.key.hashCode() & (table2.length - 1);
                p.next = table2[h];
                table2[h] = p;

                p = next;
            }
        }
        table = table2;
        updateConstant();
    }

    @Override
    public V remove(Object key) {
        int h = hash(key);
        Entry2 pre = null;
        for (Entry2 p = table[h]; p != null; pre = p, p = pre.next) {
            if (p.key.equals(key)) {
                V oldValue = (V) p.value;
                if (pre == null) {
                    table[h] = p.next;
                } else {
                    pre.next = p.next;
                }
                count--;
                return oldValue;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }


    private static class Entry2<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry2<K, V> next;

        public Entry2(K key, V value, Entry2 next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
