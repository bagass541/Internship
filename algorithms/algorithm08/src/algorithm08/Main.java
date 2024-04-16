package algorithm08;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(isValid(new char[] {'n','s','n','s','n','s','n','s','n','s'}));
		System.out.println(isValid(new char[] {'w','e','w','e','w','e','w','e','w','e','w','e'}));
		System.out.println(isValid(new char[] {'w'}));
		System.out.println(isValid(new char[] {'n','n','n','s','n','s','n','s','n','s'}));
		System.out.println(isValid(new char[] {'n','n','n','n','n','s','s','s','s','s'}));
	}
	
	public static boolean isValid(char[] walk) {
	    if(walk.length != 10) return false;
	    
	    Map<Character, Integer> map = new HashMap<Character, Integer>();
	    for(char c : walk) {
	    	if(map.containsKey(c)) {
	    	  	map.put(c, map.get(c) + 1);
	    	} else {
	    		map.put(c, 1);
	    	}
	    }
	    
	    return map.getOrDefault('n', 0).equals(map.getOrDefault('s', 0)) && 
	    		map.getOrDefault('e', 0).equals(map.getOrDefault('w', 0));
	}
}
