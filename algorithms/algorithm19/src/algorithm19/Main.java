package algorithm19;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(duplicateCount("abcde"));
		System.out.println(duplicateCount("abcdea"));
		System.out.println(duplicateCount("Indivisibilities"));

	}

	public static int duplicateCount(String text) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int count = 0;
		text = text.toLowerCase();
		
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if(map.containsKey(ch)) {
				if(map.put(ch, map.get(ch) + 1) == 1) {
					count++;
				}
			} else {
				map.put(ch, 1);
			}
		}
		
		return count;
	}
}
