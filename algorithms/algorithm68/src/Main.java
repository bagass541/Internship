public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 1};
        int[] nums2 = new int[] {1,2,1,3,5,6,4};
        int[] nums3 = new int[] {1};
        System.out.println(findPeakElement(nums3));
    }

    public static int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        return findPeakElement(nums, start, end);
    }

    public static int findPeakElement(int[] nums, int start, int end) {
        int mid = (start + end) / 2;

        long leftNeighbour = mid == 0 ? Long.MIN_VALUE : nums[mid - 1];
        long rightNeighbour = mid == nums.length - 1 ? Long.MIN_VALUE : nums[mid + 1];

        if (nums[mid] > leftNeighbour && nums[mid] > rightNeighbour) {
            return mid;
        } else if(nums[mid] < rightNeighbour){
            return findPeakElement(nums, mid + 1, end);
        } else {
            return findPeakElement(nums, start, mid - 1);
        }
    }
}