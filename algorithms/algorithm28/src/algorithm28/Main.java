package algorithm28;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		Arrays.stream(productFib(714)).forEach(l -> System.out.print(l + " "));
		System.out.println();
		Arrays.stream(productFib(800)).forEach(l -> System.out.print(l + " "));
		System.out.println();
		Arrays.stream(productFib(4895)).forEach(l -> System.out.print(l + " "));
		System.out.println();
		Arrays.stream(productFib(2504730781961L * 17167680177565L)).forEach(l -> System.out.print(l + " "));
	}
	
	public static long[] productFib(long prod) {
		long firstNumber = 0;
		long secondNumber = 1;
		
		while(firstNumber * secondNumber <= prod) {
			long prev = secondNumber;
			secondNumber = firstNumber + secondNumber;
			firstNumber = prev;
		}
		
		return new long[] {firstNumber, secondNumber, firstNumber * secondNumber == prod ? 1 : 0};
	}
}
