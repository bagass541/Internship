package algorithm02;

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

	 public static String whoLikesIt(String... names) {
		 int length = names.length;
		 
		 switch(length) {
			 case 0: return "no one likes this";
			 case 1: return names[0] + " likes this";
			 case 2: return names[0] + " and " + names[1] + " like this";
			 case 3: return names[0] + ", " + names[1] + " and " + names[2] + " like this";
			 default: return names[0] + ", " + names[1] + " and " + String.valueOf(length - 2) + " others like this";
		 }
	}	
}
