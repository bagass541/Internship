package cache.cache;

public class Node<K, V> implements Comparable<Node<K, V>> {

	K key;
	
	V value;
	
	int frequency;

	public Node(K key, V value, int frequency) {
		this.key = key;
		this.value = value;
		this.frequency = frequency;
	}

	@Override
	public int compareTo(Node<K, V> o) {
		return o.frequency - this.frequency;
	}
}
