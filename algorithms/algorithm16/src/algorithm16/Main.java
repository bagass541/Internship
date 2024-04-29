package algorithm16;

public class Main {

	public static void main(String[] args) {
		System.out.println(countBits(1234));

	}
	
	public static int countBits(int n){
		return Integer.bitCount(n);
	}
}
