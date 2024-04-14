package algorithm12;

public class Main {

	public static void main(String[] args) {
		System.out.println(digital_root(16));
		System.out.println(digital_root(942));
		System.out.println(digital_root(132189));
	}
	
	public static int digital_root(int n) {	
		 while(n > 9) {
			 n = n / 10 + n % 10;
		 }
		 
		 return n;
	}

}
