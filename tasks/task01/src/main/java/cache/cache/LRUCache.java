package cache.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> implements Cache<K, V> {

	private LinkedHashMap<K, V> linkedHashMap;
	
	@SuppressWarnings("serial")
	public LRUCache(int capacity) {
		linkedHashMap = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > capacity;
			}
		};
	}
	
	@Override
	public void put(K key, V value) {
		if(!linkedHashMap.containsKey(key)) {
			putIfAbsent(key, value);
		} else {
			linkedHashMap.put(key, value);
		}	
	}
	
	@Override
	public void putIfAbsent(K key, V value) {
		linkedHashMap.putIfAbsent(key, value);
	}
	
	@Override
	public void delete(K key) {
		if(linkedHashMap.containsKey(key)) {
			linkedHashMap.remove(key);
		}	
	}
	
	@Override
	public Optional<V> get(K key) {
		return Optional.ofNullable(linkedHashMap.get(key));
	}
	
	@Override
	public int size() {
		return linkedHashMap.size();
	}
	
	@Override
	public boolean isEmpty() {
		return linkedHashMap.isEmpty();
	}
	
	@Override
	public void clear() {
		linkedHashMap.clear();
	}

	@Override
	public boolean contains(V value) {
		return linkedHashMap.containsValue(value);
	}
	
}
