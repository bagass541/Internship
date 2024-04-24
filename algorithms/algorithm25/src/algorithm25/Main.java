package algorithm25;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(firstNonRepeatingLetter("a"));
		System.out.println(firstNonRepeatingLetter("streSS"));
		System.out.println(firstNonRepeatingLetter("moon-men"));
		System.out.println(firstNonRepeatingLetter("I Like To Take Candy From A Baby"));
	}

	
	public static String firstNonRepeatingLetter(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] chArray  = s.toCharArray();
		
		for(char c : chArray) {
			c = Character.toLowerCase(c);
			int prev = map.containsKey(c) ? map.get(c) : 0;
			map.put(c, prev + 1);
		}
		
		for(char c : chArray) {
			if(map.get(Character.toLowerCase(c)) == 1) {
				return String.valueOf(c);
			}
		}
		
		return "";
	}
}
