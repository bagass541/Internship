public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, -2, 3, -2};
        int[] nums2 = new int[]{5, -3, 5};
        int[] nums3 = new int[]{-3, -2, -3};
        System.out.println(maxSubarraySumCircular(nums3));
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0];
        int minSum = nums[0];
        int currMaxSum = 0;
        int currMinSum = 0;
        int totalSum = 0;

        for (int num : nums) {
            currMaxSum = Math.max(currMaxSum + num, num);
            maxSum = Math.max(currMaxSum, maxSum);

            currMinSum = Math.min(currMinSum + num, num);
            minSum = Math.min(currMinSum, minSum);

            totalSum += num;
        }

        return maxSum > 0 ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }
}