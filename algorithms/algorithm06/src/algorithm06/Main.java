package algorithm06;

public class Main {

	public static void main(String[] args) {
		System.out.println(sequence(new int[] {}));

	}
	
	public static int sequence(int[] arr) {
		int maxSoFar = 0;
		int maxEnding = 0;
		
		for (int i = 0; i < arr.length; i++) {
			maxEnding += arr[i];
			
			if(maxSoFar < maxEnding) {
				maxSoFar = maxEnding;
			}
			
			if(maxEnding < 0) {
				maxEnding = 0;
			}
		}
		
		return maxSoFar;
	}

}
