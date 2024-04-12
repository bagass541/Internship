package algorithm08;

public class Main {

	public static void main(String[] args) {
		System.out.println(isValid(new char[] {'n','s','n','s','n','s','n','s','n','s'}));
		System.out.println(isValid(new char[] {'w','e','w','e','w','e','w','e','w','e','w','e'}));
		System.out.println(isValid(new char[] {'w'}));
		System.out.println(isValid(new char[] {'n','n','n','s','n','s','n','s','n','s'}));
		System.out.println(isValid(new char[] {'n','n','n','n','n','s','s','s','s','s'}));
	}
	
	public static boolean isValid(char[] walk) {
	    if(walk.length != 10) return false;
	    
	    int x = 0;
	    int y = 0;
	    
	    for(char c : walk) {
	    	switch(c) {
		    	case 'n' -> y++;
		    	case 's' -> y--;
		    	case 'e' -> x++;
		    	case 'w' -> x--;
 	    	}
	    }
	    
	    return x == 0 && y == 0;
	}
}
