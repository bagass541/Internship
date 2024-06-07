import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};

        kSmallestPairs(nums1, nums2, 3);
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> que = new PriorityQueue<>((a, b) -> a.get(0) + a.get(1)
                - b.get(0) - b.get(1));

        List<List<Integer>> res = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }

        for (int i = 0; i < nums1.length && i < k; i++) {
            que.offer(Arrays.asList(nums1[i], nums2[0], 0));
        }

        while (k-- > 0 && !que.isEmpty()) {
            List<Integer> curr = que.poll();
            res.add(Arrays.asList(curr.get(0), curr.get(1)));

            if (curr.get(2) == nums2.length - 1) {
                continue;
            }

            que.offer(Arrays.asList(curr.get(0), nums2[curr.get(2) + 1], curr.get(2) + 1));
        }

        return res;
    }
}