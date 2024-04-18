package task02;

import java.util.Map;
import java.util.Objects;

public class CustomEntry<K, V> implements Map.Entry<K, V> {

	int hash;
	
	K key;
	
	V value;
	
	CustomEntry<K, V> next;
	
	public CustomEntry(int hash, K key, V value, CustomEntry<K, V> next) {
		this.hash = hash;
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

	@Override
	public int hashCode() {
		return Objects.hash(key) ^ Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) 
			return true;
		
		return obj instanceof CustomEntry<?, ?> e
			&& Objects.equals(key, e.getKey())
			&& Objects.equals(value, e.getValue());
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}	
}
