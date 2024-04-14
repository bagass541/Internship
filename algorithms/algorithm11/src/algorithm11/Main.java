package algorithm11;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		System.out.println(solution(10));
		System.out.println(solution(30));

	}

	public static int solution(int number) {
	    Set<Integer> multiples = new HashSet<>();
	    
	    int i = 3;
	    int j = 5;
	    
	    while(j < number || i < number) {	    	
	    	multiples.add(i);
	    	if(number > j) multiples.add(j);
	    	
	    	j += 5;
	    	i += 3;
	    }
	    
	    return multiples.stream().reduce((x, y) -> x + y).get();
	}
}
