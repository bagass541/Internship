package algorithm26;

public class Main {

	public static void main(String[] args) {
		System.out.println(scramble("rkqodlw", "world"));
		System.out.println(scramble("cedewaraaossoqqyt", "codewars"));
		System.out.println(scramble("katas", "steak"));
	}

	public static boolean scramble(String str1, String str2) {
		if(str1.length() < str2.length()) return false;
		
		int[] alphabet = new int[26];
		
		for(char c : str1.toCharArray()) {
			alphabet[c - 'a']++;
		}
		
		for(char c : str2.toCharArray()) {
			alphabet[c - 'a']--;
			
			if(alphabet[c - 'a'] < 0) return false;
		}
		
		return true;
	}
}
