package algorithm17;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(find(new int[] {2, 4, 0, 100, 4, 11, 2602, 36}));
		System.out.println(find(new int[] {160, 3, 1719, 19, 11, 13, -21}));

	}
	
	static int find(int[] integers) {
		int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
		int mod = sum >= 2 ? 0 : 1;
		
		return Arrays.stream(integers).filter(i -> Math.abs(i) % 2 == mod).findAny().getAsInt();
	}
		

}
