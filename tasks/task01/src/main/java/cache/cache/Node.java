package cache.cache;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Node<K, V> implements Comparable<Node<K, V>> {

	private K key;
	
	private V value;
	
	private int frequency;
	
	@Override
	public int compareTo(Node<K, V> o) {
		return o.frequency - this.frequency;
	}
}
