public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[] {3,4,5,1,2};
        int[] nums2 = new int[] {4,5,6,7,0,1,2};
        int[] nums3 = new int[] {11,13,15,17};
        int[] nums4 = new int[] {5,1,2,3,4};
        System.out.println(findMin(nums1));
    }

    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[start] <= nums[mid]) {
                min = Math.min(min, nums[start]);
                start = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                end = mid - 1;
            }
        }

        return min;
    }
}