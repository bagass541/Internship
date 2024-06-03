public class Main {

    public static void main(String[] args) {
        int[] nums = new int[] {5,7,7,8,8,10};
        int[] nums2 = new int[] {0};
        int[] result = searchRange(nums, 8);

        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int firstOccurence = findFistPosition(nums, target);
        int lastOccurence = findLastPosition(nums, target);

        return new int[] {firstOccurence, lastOccurence};
    }

    public static int findFistPosition(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int index = -1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] >= target) {
                end = mid - 1;
            } else  {
                start = mid + 1;
            }

            if(nums[mid] == target) {
                index = mid;
            }
        }

        return index;
    }

    public static int findLastPosition(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int index = -1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

            if(nums[mid] == target) {
                index = mid;
            }
        }

        return index;
    }
}