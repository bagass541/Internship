import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        System.out.println(res);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis(ans, "", n, 0, 0);

        return ans;
    }

    private static void generateParenthesis(List<String> ans, String comb, int n, int open, int close) {
        if(n * 2 == open + close) {
            ans.add(comb);
            return;
        }

        if(open > close) {
            generateParenthesis(ans, comb + ")", n, open, close + 1);
        }

        if (open < n) {
            generateParenthesis(ans, comb + "(", n, open + 1, close);
        }
    }
}