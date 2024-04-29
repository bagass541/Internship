package algorithm22;

public class Main {

	public static void main(String[] args) {
		System.out.println(makeReadable(0));
		System.out.println(makeReadable(5));
		System.out.println(makeReadable(60));
		System.out.println(makeReadable(3600));
		System.out.println(makeReadable(86399));

	}

	public static String makeReadable(int seconds) {
		int minutes = 0;
		int hours = 0;
		
		while(seconds >= 60) {
			
			if (minutes < 59) {
				minutes++;
				seconds -= 60;
			} else {
				hours++;
				minutes -= 60;
			}
		}
		
 		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
