package cache.cache;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLRU {
	
	LRUCache<Integer, Integer> lruCache;
	
	@BeforeEach
	void setUp() {
		this.lruCache = new LRUCache<Integer, Integer>(5);
	}

	@Test
	void testSize() {
		Assertions.assertEquals(0, lruCache.size());
		
		lruCache.put(1, 1);
		Assertions.assertEquals(1, lruCache.size());
	}

	@Test
	void testPut() {	
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		lruCache.put(4, 4);
		
		Assertions.assertFalse(lruCache.isEmpty());
		Assertions.assertEquals(3, lruCache.get(3).get());
		Assertions.assertEquals(Optional.empty(), lruCache.get(5));
	}
	
	@Test
	void testGet() {	
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		
		lruCache.get(1);
		lruCache.get(2);
		
		lruCache.put(4, 4);
		lruCache.put(5, 5);
		lruCache.put(6, 6);
		
	
		Assertions.assertEquals(2, lruCache.get(2).get());
		Assertions.assertEquals(Optional.empty(), lruCache.get(3));
	}
	
	@Test
	void testIsEmpty() {
		Assertions.assertTrue(lruCache.isEmpty());
	}
	
	@Test
	void testClear() {
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		lruCache.put(4, 4);
		
		lruCache.clear();
		
		System.out.println(lruCache.isEmpty());
		Assertions.assertTrue(lruCache.isEmpty());
		
	}
	
	@Test
	void testContains() {
		lruCache.put(1, 1);
		
		Assertions.assertTrue(lruCache.contains(1));
		Assertions.assertFalse(lruCache.contains(5));
	}
	
	@Test
	void testDelete() {
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.put(3, 3);
		
		Assertions.assertTrue(lruCache.contains(1));
		lruCache.delete(1);
		Assertions.assertFalse(lruCache.contains(1));
	}
}
