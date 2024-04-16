package algorithm14;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(findIt(new int[] {0, 1, 0, 1, 0}));
		System.out.println(findIt(new int[] {1,2,2,3,3,3,4,3,3,3,2,2,1}));

	}

	public static int findIt(int[] a) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < a.length; i++) {
			int freqToSet = map.getOrDefault(a[i], 0) + 1;
			map.put(a[i], freqToSet);
		}
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(entry.getValue() % 2 != 0) {
				return entry.getKey();
			}
		}
		return 0;
	}
}
