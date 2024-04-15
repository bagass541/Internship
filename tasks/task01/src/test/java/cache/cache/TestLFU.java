package cache.cache;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLFU {

	LFUCache<Integer, TestObject> lfuCache;
	
	@BeforeEach
	void setUp() {
		lfuCache = new LFUCache<Integer, TestObject>(3);
		lfuCache.put(1, new TestObject(1));
		lfuCache.put(2, new TestObject(2));
		lfuCache.put(3, new TestObject(3));
	}
	
	@Test
	void testSize() {
		Assertions.assertEquals(3, lfuCache.size());
	}

	@Test
	void testPut() {	
		Assertions.assertFalse(lfuCache.isEmpty());
		Assertions.assertEquals(2, lfuCache.get(2).get().getI());
		Assertions.assertEquals(Optional.empty(), lfuCache.get(5));
		
		lfuCache.get(1);
		lfuCache.get(2);
		lfuCache.get(1);
		lfuCache.get(1);
		lfuCache.get(2);
		
		lfuCache.put(4, new TestObject(4));
		lfuCache.put(5, new TestObject(5));
		
		Assertions.assertEquals(Optional.empty(), lfuCache.get(3));
	}
	
	@Test
	void testGet() {	
		Assertions.assertEquals(3, lfuCache.get(3).get().getI());
		Assertions.assertEquals(2, lfuCache.get(2).get().getI());
		Assertions.assertEquals(Optional.empty(), lfuCache.get(4));
	}
	
	@Test
	void testIsEmpty() {
		Assertions.assertFalse(lfuCache.isEmpty());
	}
	
	@Test
	void testClear() {
		Assertions.assertFalse(lfuCache.isEmpty());
		lfuCache.clear();
		Assertions.assertTrue(lfuCache.isEmpty());
	}
	
	@Test
	void testDelete() {
		Assertions.assertEquals(3, lfuCache.get(3).get().getI());
		lfuCache.delete(3);
		Assertions.assertEquals(Optional.empty(), lfuCache.get(3));
	}
}
