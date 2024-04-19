package task02;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomMap<K, V> implements Map<K, V>{
	
	private static final int MAXIMUM_CAPACITY = 1 << 30;
	
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private static final int DEFAULT_INITIAL_CAPACITY  = 1 << 4;

	private CustomEntry<K, V>[] table;
	
	private Set<Entry<K, V>> entrySet;
	
	private final float loadFactor;
	
	// Следующее значение размера таблицы
	private int thresHold;
	
	private int size;
	
	public CustomMap(int initialCapacity, float loadFactor) {
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		}
		
		if(initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if(loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
		}
		
		this.loadFactor = loadFactor;
		entrySet = new HashSet<Map.Entry<K,V>>();
		this.thresHold = tableSizeFor(initialCapacity);
		System.out.println("this.threshold: " + this.thresHold);
	}
	
	public CustomMap(int initialCap) {
		this(initialCap, DEFAULT_LOAD_FACTOR);
	}

	public CustomMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	private int tableSizeFor(int initialCapacity) {
		int n = 1;
		if(initialCapacity < DEFAULT_INITIAL_CAPACITY) {
			return DEFAULT_INITIAL_CAPACITY;
		}
		
		while(n < initialCapacity) {
			n <<= 1;
		}
		return n;
	}

	private int hash(Object key) {
		int h;
		return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return entrySet.stream().anyMatch(entry -> entry.getKey().equals(key));
	}

	@Override
	public boolean containsValue(Object value) {
		return entrySet.stream().anyMatch(entry -> entry.getValue().equals(value));
	} 

	@Override
	public V get(Object key) {
		CustomEntry<K, V> e;
		return (e = getNode(hash(key), key)) == null ? null : e.value;
	}
	
	private final CustomEntry<K, V> getNode(int hash, Object key) {
		CustomEntry<K, V> first;
		CustomEntry<K, V> e;
		int n;
		K k;
	//	System.out.println("threshold: " + thresHold);
		// (n - 1) & hash эквивалент hash % n, это позволяет обойтись без деления для оптимизации, то есть 
		// если hash = 34, а n = 16, то выведет 2. 
		// Также это нужно, чтобы не создавать слишком большой массив и этим действием узнавать индекс элемента
		if(table != null && (n = table.length) > 0 &&
				(first = table[(n - 1) & hash]) != null) {
			if(first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k)))) {
				return first;
			}
			
			if((e = first.next) != null) {
				do {
					if(e.hash == hash &&
							((k = e.key) == key || (key != null && key.equals(k)))) {
						return e;
					}
				} while((e = e.next) != null);
			}
		}
		
		return null;
	}

	@Override
	public V put(K key, V value) {
		return putVal(hash(key), key, value);
	}
	
	private V putVal(int hash, K key, V value) {
	
		int i;
		CustomEntry<K, V> p;
	
		System.out.println("threshold: " + thresHold);
		if(table == null) {
			table = resize();
		}
		int n = table.length;
		if(size >= table.length * loadFactor) {
			table = resize();
		}
		
		if((p = table[i = (n - 1) & hash]) == null) {
			 p = new CustomEntry<K, V>(hash, key, value, null);
			 table[i] = p;
			 size++;
		} else {
			K k;
			
			if(p.hash == hash && 
					((k = p.key) == key || (key != null && key.equals(k)))) {
				V oldValue = p.value;
				p.setValue(value);
				
				return oldValue;
			} else {
				while(p.next != null) {
					p = p.next;
				}
				
				p.next = new CustomEntry<K, V>(hash, key, value, null);
				p = p.next;
				size++;
			}
		}

		entrySet.add(p);
		return p.value;
	}
	
	
	@SuppressWarnings("unchecked")
	private CustomEntry<K, V>[] resize() {
		CustomEntry<K, V>[] oldTab = table;
		int oldCap = (oldTab == null) ? 0 : oldTab.length;
		int oldThr = thresHold;
		int newCap;
		int newThr = 0;
		
		if(oldCap > 0) {
			if(oldCap >= MAXIMUM_CAPACITY) {
				return oldTab;
			} else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && 
					oldCap >= DEFAULT_INITIAL_CAPACITY) {
				newThr = oldThr << 1;
			}
		} else if(oldThr > 0) {
			newCap = oldThr;
		} else {
			newCap = DEFAULT_INITIAL_CAPACITY;
			newThr = tableSizeFor(DEFAULT_INITIAL_CAPACITY);
		}
		
		if(newThr == 0) {
			newThr = newCap << 1;
		}
		thresHold = newThr;
		
		CustomEntry<K, V>[] newTab = (CustomEntry<K, V>[]) new CustomEntry[newCap];
		CustomEntry<K, V> e;
		// rehashing, чтобы не потерять элементы
		if(oldTab != null) {
			for (int i = 0; i < oldTab.length; i++) {
				if((e = oldTab[i]) != null) {
					newTab[(newCap - 1) & e.hash] = e;
				}
			}
		}
		return newTab;
	}

	@Override
	public V remove(Object key) {
		return removeNode(hash(key), key);
	}
	
	@SuppressWarnings("unchecked")
	private V removeNode(int hash, Object key) {
		int n;
		if(table == null || (n = table.length) == 0) return null;
		if(!containsKey(key)) return null;
		
		CustomEntry<K, V> entryToDelete;
		int i;
		if((entryToDelete = table[i = (n - 1) & hash]) == null) {
			return null;
		} else {
			K k;
			if((k = (K) key) != entryToDelete.key || (k != null && !k.equals(key))) {
				while(entryToDelete.next != null && entryToDelete.key != k) {
					entryToDelete = entryToDelete.next;
				}
			}
		}

		entrySet.remove(entryToDelete);
		table[i] = null;
		size--;
		
		return entryToDelete.value;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		int n = m.size() + size;
		if(n > 0) {
			if(table == null) {
				table = resize();
			}
			
			while(n > thresHold && table.length < MAXIMUM_CAPACITY) {
				resize();
			}
		}
		
		for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
			K key = entry.getKey();
			putVal(hash(key), key, entry.getValue());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		table = (CustomEntry<K, V>[]) new CustomEntry[DEFAULT_INITIAL_CAPACITY];

		entrySet.clear();
		size = 0;
		
	}

	@Override
	public Set<K> keySet() {
		return entrySet.stream().map(e -> e.getKey()).collect(Collectors.toSet());
	}

	@Override
	public Collection<V> values() {
		return entrySet.stream().map(e -> e.getValue()).collect(Collectors.toSet());
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return entrySet;
	}
}
