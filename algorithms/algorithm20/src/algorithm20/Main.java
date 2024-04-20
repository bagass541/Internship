package algorithm20;

public class Main {

	public static void main(String[] args) {
		System.out.println(persistence(39));
		System.out.println(persistence(999));
		System.out.println(persistence(4));
		System.out.println(persistence(25));
	}
	
	public static int persistence(long n) {
		int count = 0;
		
		while(n > 9) {
			int multiplied = 1;
			
			while(n > 0) {
				multiplied *= n % 10;
				n /= 10;
			}
			
			n = multiplied;
			count++;
		}
		
		return count; 
	}

}
