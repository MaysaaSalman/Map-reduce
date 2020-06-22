package mapreduce;

import java.io.Serializable;
import java.util.Objects;

public class Record<K extends Comparable, V> implements Serializable,Comparable {
    private static final long serialVersionUID = 38588276511677159L;

    private K key;
    private V value;

    public Record(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public String toString() {
        return key + ": " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record<?, ?> record = (Record<?, ?>) o;

        if (!Objects.equals(key, record.key)) return false;
        return Objects.equals(value, record.value);
    }



    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Record<?, ?> record = (Record<?, ?>) o;
        return this.key.compareTo(record.key);
    }
}