import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        combine(8, 3);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combine(ans, new ArrayList<Integer>(), 1, n, k);
        return ans;
    }

    public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if(k == 0) {
            combs.add(new ArrayList<>(comb));
            return;
        }

        int lastPossibleStarter = n - k + 1;
        for (int i = start; i <= lastPossibleStarter; i++) {
            comb.add(i);
            combine(combs, comb, i + 1, n, k - 1);
            comb.remove(comb.size() - 1);
        }
    }
}