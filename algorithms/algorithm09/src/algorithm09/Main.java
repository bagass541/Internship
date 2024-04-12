package algorithm09;

public class Main {

	public static void main(String[] args) {
		System.out.println(alphanumeric("abcdefghijklmnopqr"));
		System.out.println(alphanumeric("..."));
		System.out.println(alphanumeric("punctuation."));

	}
	
	public static boolean alphanumeric(String s){
		return s.matches("[A-Za-z0-9]+");
	}

}
