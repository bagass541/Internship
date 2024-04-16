package algorithm12;

public class Main {
	 
	 public static int digital_root(int n) {
		 while (n > 9) {
			 int sum = 0;
			 while (n > 0) {
				 int digit = n % 10;
				 sum += digit;
				 n /= 10;
			 }
			 n = sum;
		 }
		 return n;
	 }
}
