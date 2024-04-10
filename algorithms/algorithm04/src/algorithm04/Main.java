package algorithm04;

public class Main {

	public static void main(String[] args) {
		char[] array = new char[] {'O','Q','R','S'};
		System.out.println(findMissingLetter(array));
	}
	
	public static char findMissingLetter(char[] array){
		int ind = array[0];
		for (int i = 1; i < array.length; i++) {
			if(array[i] != ind + 1) {
				return (char) (ind + 1);
			}
			ind++;
		}
	    return ' ';
	}

}
