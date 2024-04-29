package algorithm31;

public class Main {

	public static void main(String[] args) {
		System.out.println(zeros(6));
		System.out.println(zeros(14));
		System.out.println(zeros(20));
		System.out.println(zeros(1000));
	}

	public static int zeros(int n) {
		int sum = 0;
		while (n > 0) {
			n /= 5;
			sum += n;
		}

		return sum;
	}
}
