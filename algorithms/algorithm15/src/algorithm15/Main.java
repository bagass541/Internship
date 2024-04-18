package algorithm15;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));

	}

	public static String createPhoneNumber(int[] numbers) {
		StringBuilder phoneNumbers = new StringBuilder();
		Arrays.stream(numbers).forEach(i -> phoneNumbers.append(i));
		
		return new StringBuilder(phoneNumbers)
				.insert(0, "(")
				.insert(4, ")")
				.insert(5, " ")
				.insert(9, "-").toString();
	}
}
