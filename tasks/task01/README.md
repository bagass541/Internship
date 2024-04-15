## LRU & LFU caches

LRU is a cache that is designed to contain a fixed size of the most recently used elements. So it means that if you want to add a new element in the full cache, it will delete the least recently used element and put the new element. It has different methods such as get(), put(), contains(), putIfAbsent(), delete(), size(), isEmpty(), clear().

LFU is a cache that is designed to contain a fixed size of the most frequency used elements. So it means that if you want to add a new element in the full cache, it will delete the least frequency used element and put the new element. It has the same methods as LRU.