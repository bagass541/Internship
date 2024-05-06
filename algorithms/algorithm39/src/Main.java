public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = node2;
        root.right = node3;

        System.out.println(sumNumbers(root));
    }

    public static int sumNumbers(TreeNode root) {
        return sumHelper(root, 0);
    }

    private static int sumHelper(TreeNode curr, int num) {
        if (curr == null) {
            return 0;
        }

        num = num * 10 + curr.val;

        if (curr.left == null && curr.right == null) {
            return num;
        }

        return sumHelper(curr.left, num) + sumHelper(curr.right, num);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}