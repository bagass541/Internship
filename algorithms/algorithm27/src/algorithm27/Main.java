package algorithm27;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Arrays.stream(dirReduc(new String[] {"\"NORTH\"", "\"SOUTH\"", "\"SOUTH\"", "\"EAST\"", "\"WEST\"", "\"NORTH\"", "\"WEST\""})).forEach(s -> System.out.println(s));
		System.out.println();
		Arrays.stream(dirReduc(new String[] {"\"NORTH\"", "\"SOUTH\"",  "\"EAST\"", "\"WEST\""})).forEach(s -> System.out.println(s));
		System.out.println();
		Arrays.stream(dirReduc(new String[] {"\"NORTH\"", "\"EAST\"", "\"WEST\"", "\"SOUTH\"", "\"WEST\"", "\"NORTH\""})).forEach(s -> System.out.println(s));
	}

	 public static String[] dirReduc(String[] arr) {
		 Stack<String> stack = new Stack<>();
		 Map<String, String> map = new HashMap<>();
		 map.put("\"NORTH\"", "\"SOUTH\"");
		 map.put("\"WEST\"", "\"EAST\"");
		 map.put("\"EAST\"", "\"WEST\"");
		 map.put("\"SOUTH\"", "\"NORTH\"");
		 
		 for(String str : arr) {
			 if(!stack.isEmpty() && map.get(str).equals(stack.peek())) {
				 stack.pop();
			 } else {
				 stack.add(str);
			 }
		 }
		 
		 return stack.toArray(new String[0]);
	 }
}
