package task02;

public class Main {

	public static void main(String[] args) {
		CustomMap<Integer, Integer> map = new CustomMap<Integer, Integer>();
		
		System.out.println("Put: " + map.put(5, 5));
		System.out.println("Get: " + map.get(5));
		System.out.println("Put: " + map.put(7, 7));
		System.out.println("Get: " + map.get(7));
		System.out.println("Size: " + map.size());
		
		map.keySet().forEach(i -> System.out.print(i + " "));
		System.out.println();
		map.values().forEach(i -> System.out.print(i + " "));
		System.out.println();
		map.entrySet().forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("Contains key 5: " + map.containsKey(5));
		System.out.println("Contains value 7: " + map.containsValue(7));
		System.out.println("Contains key 10: " + map.containsKey(10));
		
		CustomMap<Integer, Integer> m = new CustomMap<Integer, Integer>();
		m.put(1, 1);
		m.put(2, 2);
		
		map.putAll(m);
		
		map.entrySet().forEach(i -> System.out.print(i + " "));
		
		System.out.println(map.size());
		
		System.out.println("Get 5: " + map.get(5));
	}

}
