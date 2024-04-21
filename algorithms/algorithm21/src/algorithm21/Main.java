package algorithm21;

public class Main {

	public static void main(String[] args) {
		System.out.println(pigIt("Pig latin is cool"));
		System.out.println(pigIt("Hello world !"));
		

	}

	public static String pigIt(String str) {
		return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
	}
}
