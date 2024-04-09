package algorithm01;

// https://www.codewars.com/kata/517abf86da9663f1d2000003/java
public class Main {

	public static void main(String[] args) {
		String s = "the_Stealth_Warrior";
		System.out.println(toCamelCase(s));

	}

	static String toCamelCase(String s){
		String[] strs = s.split("[-_]");
		StringBuilder sb = new StringBuilder();
		
		sb.append(strs[0]);
		
		for(int i =1; i < strs.length; i++) {
			sb.append(Character.toUpperCase(strs[i].charAt(0)) + strs[i].substring(1));
		}
	    return sb.toString();
	}
	
}
