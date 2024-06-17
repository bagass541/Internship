public class Main {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        int[] ways = new int[n + 1];
        ways[n] = 1;
        ways[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            ways[i] = ways[i + 1] + ways[i + 2];
        }

        return ways[0];
    }
}