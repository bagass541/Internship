import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] candidates = new int[] {2, 3, 6, 7};
        List<List<Integer>> ans = combinationSum(candidates, 7);

        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j));
            }

            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum(ans, new ArrayList<>(), candidates, target, 0);

        return ans;
    }

    public static void combinationSum(List<List<Integer>> ans, List<Integer> comb, int[] candidates,
                                  int remain, int start) {
        if(remain == 0) {
            ans.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int curr = candidates[i];

            if((remain - curr) < 0) {
                continue;
            }

            comb.add(curr);

            combinationSum(ans, comb, candidates, remain - curr, i);
            comb.remove(comb.size() - 1);
        }
    }
}