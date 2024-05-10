public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static int min = Integer.MAX_VALUE;

    private static Integer prev = null;

    public static int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return  min;
        }

        getMinimumDifference(root.left);

        if(prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        getMinimumDifference(root.right);

        return min;
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