package algorithm19;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		System.out.println(duplicateCount("abcde"));
		System.out.println(duplicateCount("abcdeaa"));
		System.out.println(duplicateCount("Indivisibilities"));

	}

	public static int duplicateCount(String text) {
		text = text.toLowerCase();
		return (int) text.chars()
				.mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() >= 2)
				.count();
	}
}
