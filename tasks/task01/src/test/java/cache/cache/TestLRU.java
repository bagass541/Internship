package cache.cache;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLRU {
	
	LRUCache<Integer, TestObject> lruCache;
	
	@BeforeEach
	void setUp() {
		this.lruCache = new LRUCache<Integer, TestObject>(3);
		lruCache.put(1, new TestObject(1));
		lruCache.put(2, new TestObject(2));
		lruCache.put(3, new TestObject(3));
	}

	@Test
	void testSize() {
		Assertions.assertEquals(3, lruCache.size());
	}

	@Test
	void testPut() {	
		Assertions.assertFalse(lruCache.isEmpty());
		Assertions.assertEquals(3, lruCache.get(3).get().getI());
		Assertions.assertEquals(Optional.empty(), lruCache.get(5));
	}
	
	@Test
	void testGet() {	
		lruCache.get(1);
		lruCache.get(2);
		
		lruCache.put(4, new TestObject(4));
		lruCache.put(5, new TestObject(5));
		
		Assertions.assertEquals(2, lruCache.get(2).get().getI());
		Assertions.assertEquals(Optional.empty(), lruCache.get(3));
	}
	
	@Test
	void testIsEmpty() {
		Assertions.assertFalse(lruCache.isEmpty());
	}
	
	@Test
	void testClear() {
		lruCache.clear();
		Assertions.assertTrue(lruCache.isEmpty());
	}
	
	@Test
	void testContains() {
		Assertions.assertTrue(lruCache.contains(new TestObject(3)));
		Assertions.assertFalse(lruCache.contains(new TestObject(7)));
	}
	
	@Test
	void testDelete() {
		Assertions.assertTrue(lruCache.contains(new TestObject(1)));
		lruCache.delete(1);
		Assertions.assertFalse(lruCache.contains(new TestObject(1)));
	}
}
