import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(greedy(new int[] {5,1,3,4,1}));
        System.out.println(greedy(new int[] {1,1,1,3,1}));
    }

    public static int greedy(int[] dice) {
        int[] counts = new int[] {0, 0, 0, 0, 0, 0};
        int[] weight = new int[] {100, 0, 0, 0, 50, 0};
        int[] weight3 = new int[] {1000, 200, 300, 400, 500, 600};

        for (int die : dice) {
            counts[die - 1]++;
        }

        int result = 0;
        for (int i = 0; i < counts.length; i++) {
            result += (counts[i]/3 * weight3[i]) + (counts[i]%3 * weight[i]);
        }

        return result;
    }
}