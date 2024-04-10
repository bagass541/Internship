package algorithm03;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println(isNarcissistic(153));

	}

	public static boolean isNarcissistic(int number) {
		int n = number;
		List<Integer> digits = new ArrayList<Integer>();
		while(n != 0) {
			digits.add(n % 10);
			n /= 10;
		}
		int length = digits.size();
		int sum = digits.stream().mapToInt(d -> (int) Math.pow(d, length)).sum();

        return sum == number;
    }
}
