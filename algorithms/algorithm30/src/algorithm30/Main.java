package algorithm30;

public class Main {

	public static void main(String[] args) {
		System.out.println(incrementString("foobar23"));
		System.out.println(incrementString("foo"));
		System.out.println(incrementString("foo0042"));
		System.out.println(incrementString("foobar99"));
		System.out.println(incrementString(""));
		System.out.println(incrementString("999"));
		System.out.println(incrementString("1VQu3R?F\\]_:-)r!bQwx/L"));
	
	}
	
	public static String incrementString(String str) {
		
		if(str.length() == 0 || !Character.isDigit(str.charAt(str.length() - 1))) {
			return str + "1";
		}
		
		StringBuilder builder = new StringBuilder(str);
		int remains = 0;
		for (int i = builder.length() - 1; i >= 0; i--) {
			
			int num = Character.getNumericValue(str.charAt(i)) + 1;
			
			remains = num / 10;
			num %= 10;
		
			builder.setCharAt(i, (char) (num + '0'));
			
			if (remains == 0) {
				break;
			} else if (remains == 1 && (i == 0 || !Character.isDigit(builder.charAt(i - 1)))) {
				builder.insert(i, remains);
				break;
			}
		}
		
		return builder.toString();
	}
}
