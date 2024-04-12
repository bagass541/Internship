package algorithm09;

import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		System.out.println(alphanumeric("abcdefghijklmnopqr"));
		System.out.println(alphanumeric("..."));
		System.out.println(alphanumeric("punctuation."));

	}
	
	public static boolean alphanumeric(String s){
		if(s.isEmpty()) return false;
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		return pattern.matcher(s).matches();
	}

}
