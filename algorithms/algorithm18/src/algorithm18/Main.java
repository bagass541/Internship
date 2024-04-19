package algorithm18;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(encode("din"));
		System.out.println(encode("recede"));
		System.out.println(encode("Success"));

	}
	
	static String encode(String word) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		StringBuilder sb = new StringBuilder();
		char[] chArray = word.toLowerCase().toCharArray();
		
		for (int i = 0; i < chArray.length; i++) {
			char ch = chArray[i];
			if(!map.containsKey(ch)) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}
		}
		
		for (int i = 0; i < chArray.length; i++) {
			sb.append(map.get(chArray[i]) >= 2 ? ")" : "(");
		}
		return sb.toString();
	}

}
