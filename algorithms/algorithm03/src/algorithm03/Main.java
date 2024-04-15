package algorithm03;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(isNarcissistic(153));

	}
	
	public static boolean isNarcissistic(int number) {
		String[] digits = String.valueOf(number).split("");
		int length = digits.length;
		return number == Arrays.stream(digits)
				.mapToInt(Integer::parseInt)
				.mapToDouble(d -> Math.pow(d, length))
				.sum();	
    }
}
