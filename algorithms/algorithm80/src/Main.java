public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int rob(int[] nums) {
        int iSum = 0;
        int jSum = 0;

        for (int num : nums) {
            int temp = Math.max(num + iSum, jSum);
            iSum = jSum;
            jSum = temp;
        }

        return jSum;
    }
}