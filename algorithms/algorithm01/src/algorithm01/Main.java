package algorithm01;

import java.util.Arrays;

// https://www.codewars.com/kata/517abf86da9663f1d2000003/java
public class Main {

	public static void main(String[] args) {
		String s = "the-stealth-warrior";
		System.out.println(toCamelCase(s));
	}
	
	static String toCamelCase(String s){
		String[] strs = s.split("[-_]");
		return Arrays.stream(strs)
				.reduce((s1,s2) -> s1 + s2.substring(0, 1).toUpperCase() + s2.substring(1, s2.length())).get();
	}
	
}
