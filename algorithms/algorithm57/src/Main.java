import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] combs = new int[] {1, 2, 3};
        List<List<Integer>> ans = permute(combs);

        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j));
            }

            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permute(ans, new ArrayList<>(), nums);
        return ans;
    }

    public static void permute(List<List<Integer>> ans, List<Integer> comb, int[] nums) {
        if (nums.length == comb.size()) {
            ans.add(new ArrayList<>(comb));

            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            if(!comb.contains(curr)) {
                comb.add(curr);
                permute(ans, comb, nums);
                comb.remove(comb.size() - 1);
            }
        }
    }
}