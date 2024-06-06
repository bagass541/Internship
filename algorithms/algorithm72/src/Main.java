public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 5, 6, 4};
        int[] nums2 = new int[] {3,4,3,2,1,3,2,5,6};
        System.out.println(findKthLargest(nums2, 4));
    }

    public static int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int j = partition(nums, start, end);
            if (j < k) {
                start = j + 1;
            } else if (j > k) {
                end = j - 1;
            } else {
                break;
            }
        }

        return nums[k];
    }

    private static int partition(int[] nums, int start, int end) {
        int i = start;
        int j = end + 1;

        while (true) {
            while (i < end && nums[++i] < nums[start]) ;
            while (j > start && nums[start] < nums[--j]) ;
            if (i >= j) {
                break;
            }

            exchange(nums, i, j);
        }

        exchange(nums, start, j);
        return j;
    }

    private static void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}