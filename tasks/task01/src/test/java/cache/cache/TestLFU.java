package cache.cache;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLFU {

	LFUCache<Integer, Integer> lfuCache;
	
	@BeforeEach
	void setUp() {
		lfuCache = new LFUCache<Integer, Integer>(3);
	}
	
	@Test
	void testSize() {
		Assertions.assertEquals(3, lfuCache.size());
	}

	@Test
	void testPut() {	
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);

		
		Assertions.assertFalse(lfuCache.isEmpty());
		Assertions.assertEquals(2, lfuCache.get(2).get());
		Assertions.assertEquals(Optional.empty(), lfuCache.get(5));
		
		lfuCache.get(1);
		lfuCache.get(2);
		lfuCache.get(1);
		lfuCache.get(1);
		lfuCache.get(2);
		
		lfuCache.put(3, 3);
		lfuCache.put(4, 4);
		
		Assertions.assertEquals(Optional.empty(), lfuCache.get(3));
	}
	
	@Test
	void testGet() {	
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(3, 3);
		Assertions.assertEquals(3, lfuCache.get(3).get());
		lfuCache.put(4, 4);
		
		Assertions.assertEquals(3, lfuCache.get(3).get());
		Assertions.assertEquals(Optional.empty(), lfuCache.get(2));
		Assertions.assertEquals(4, lfuCache.get(4).get());
	}
	
	@Test
	void testIsEmpty() {
		Assertions.assertTrue(lfuCache.isEmpty());
		
		lfuCache.put(1, 1);
		Assertions.assertFalse(lfuCache.isEmpty());
	}
	
	@Test
	void testClear() {
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(3, 3);
		lfuCache.put(4, 4);
		
		lfuCache.clear();
		
		Assertions.assertTrue(lfuCache.isEmpty());
	}
	
	@Test
	void testDelete() {
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(3, 3);
		
		lfuCache.delete(3);
		
		Assertions.assertEquals(Optional.empty(), lfuCache.get(3));
	}
}
