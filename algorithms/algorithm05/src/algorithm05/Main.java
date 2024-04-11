package algorithm05;

public class Main {

	public static void main(String[] args) {
		System.out.println(solution(1253));

	}
	
	public static String solution(int n) {
		String[] strs = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] values = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		
		while(n > 0 && i < strs.length) {
			if(values[i] <= n) {
				sb.append(strs[i]);
				n -= values[i];
			} else {
				i++;
			}
		}
        return sb.toString();
    }

}
