package cache.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LFUCache<K,V> implements Cache<K, V> {
	
	private int capacity;
	
	private List<Node<K, V>> nodes;
	
	private Map<K, Node<K, V>> keyToNodes;
	
	public LFUCache(int capacity) {
		this.capacity = capacity;
		nodes = new ArrayList<Node<K,V>>();
		keyToNodes = new HashMap<K, Node<K,V>>();
	}
	
	@Override
	public Optional<V> get(K key) {
		Node<K, V> node = keyToNodes.get(key);
		if(node != null) {
			node.frequency++;
			Collections.sort(nodes);
			return Optional.ofNullable(node.value);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public int size() {
		return capacity;
	}

	@Override
	public boolean isEmpty() {
		return nodes.isEmpty();
	}

	@Override
	public void clear() {
		nodes.clear();
	}

	@Override
	public void put(K key, V value) {
		if(!nodes.contains(value)) {
			putIfAbsent(key, value);
		} else {
			Node<K, V> node = keyToNodes.get(key);
			node.value = value;
			node.frequency++;
		}
	}

	@Override
	public void putIfAbsent(K key, V value) {
		if(nodes.size() == capacity) {
			Node<K, V> valueToRemove = nodes.get(nodes.size() - 1);
			nodes.remove(valueToRemove);
			keyToNodes.remove(valueToRemove.key); 
		}
		
		Node<K, V> newNode = new Node<K, V>(key, value, 1);
		nodes.add(newNode);
		keyToNodes.put(key, newNode);
		
		Collections.sort(nodes);
		
	}

	@Override
	public void delete(K key) {
		Node<K, V> nodeToDelete = keyToNodes.get(key);
		keyToNodes.remove(key);
		
		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(nodeToDelete)) {
				nodes.remove(i);
				break;
			}
		}
		
	}

	@Override
	public boolean contains(V value) {
		return nodes.stream().anyMatch(n -> n.value == value);
	}
}


