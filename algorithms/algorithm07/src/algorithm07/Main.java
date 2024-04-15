package algorithm07;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		System.out.println(isValid("(){}[]"));
		System.out.println(isValid("([{}])"));
		System.out.println(isValid("(}"));
		System.out.println(isValid("[(])"));
		System.out.println(isValid("[({})](]"));
		System.out.println(isValid("())({}}{()][]["));
	

	}
	
	 public static boolean isValid(String braces) {
		 Stack<String> stack = new Stack<String>();
		 
		 for(String str : braces.split("")) {
			 if(str.equals("(")) stack.push(")");
			 else if(str.equals("[")) stack.push("]");
			 else if(str.equals("{")) stack.push("}");
			 else {
				 if(stack.isEmpty() || !stack.pop().equals(str)) 
					 return false;
			 }
		 }
		 System.out.println(stack.size());
		 
		 return stack.isEmpty();
	 }

}
