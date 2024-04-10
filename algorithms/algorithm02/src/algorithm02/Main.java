package algorithm02;

import java.util.HashMap;
import java.util.Map;

// https://www.codewars.com/kata/5266876b8f4bf2da9b000362/java
public class Main {

	public static void main(String[] args) {
		System.out.println(whoLikesIt());
		System.out.println(whoLikesIt("Sasha"));
		System.out.println(whoLikesIt("Sasha", "Papa"));
		System.out.println(whoLikesIt("Sasha", "Papa", "Mama"));
		System.out.println(whoLikesIt("Sasha", "Papa", "Mama", "Artem"));
		System.out.println(whoLikesIt("Sasha", "Papa", "Mama", "Artem", "Sasha", "Papa", "Mama", "Artem"));

	}

	private static Map<Integer, String> choices = new HashMap<>();

    static {
        choices.put(0, "no one likes this");
        choices.put(1, "%s likes this");
        choices.put(2, "%s and %s like this");
        choices.put(3, "%s, %s and %s like this");
    }

    public static String whoLikesIt(String... names) {
        int namesCount = names.length;
        return namesCount <= 3 ?
                String.format(choices.get(namesCount), names) :
                String.format("%s, %s and %s others like this", names[0], names[1], namesCount - 2);
    }
}
