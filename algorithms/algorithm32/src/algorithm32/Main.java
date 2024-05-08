package algorithm32;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		System.out.println(perimeter(BigInteger.valueOf(5)));
		System.out.println(perimeter(BigInteger.valueOf(7)));
		System.out.println(perimeter(BigInteger.valueOf(30)));
		System.out.println(perimeter(BigInteger.valueOf(41)));

	}

	public static BigInteger perimeter(BigInteger n) {
		BigInteger sum = BigInteger.valueOf(1);
		BigInteger firstNum = BigInteger.valueOf(0);
		BigInteger secondNum = BigInteger.valueOf(1);
		for(int i = 0; i < n.intValue(); i++) {
			BigInteger temp = secondNum;
			
			secondNum = secondNum.add(firstNum);
			firstNum = temp;
			sum = sum.add(secondNum);
		}
		
		return sum.multiply(BigInteger.valueOf(4));
	}
}
