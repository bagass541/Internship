package cache.cache;

import java.util.Optional;

public interface Cache<K, V> {
	
	void put(K key, V value);
	
	void putIfAbsent(K key, V value);
	
	void delete(K key);	
	
	boolean contains(V value);
	
	Optional<V> get(K i);
	
	int size();
	
	boolean isEmpty();
	
	void clear();
}
