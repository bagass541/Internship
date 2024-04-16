package algorithm13;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		System.out.println(spinWords("Hey fellow warriors"));
		System.out.println(spinWords("This is a test"));
		System.out.println(spinWords("This is another test"));
		System.out.println(spinWords("Welcome"));
	}

	public static String spinWords(String sentence) {
		return Arrays.stream(sentence.split(" "))
				.map(s -> s.length() >= 5 ? new StringBuilder(s).reverse().toString() : s)
				.collect(Collectors.joining(" "));
	}
}
