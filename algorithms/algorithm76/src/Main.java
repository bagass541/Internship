import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] res = plusOne(new int[] {1, 2, 9});
        int[] res2 = plusOne(new int[] {9});

        Arrays.stream(res2).forEach(System.out::println);
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
}