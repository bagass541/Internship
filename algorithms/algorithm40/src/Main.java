public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(0);
        TreeNode node9 = new TreeNode(8);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;

        node5.left = node6;
        node5.right = node7;

        node3.left = node8;
        node3.right = node9;

        System.out.println(lowestCommonAncestor(root, node2, node3).val);
        System.out.println(lowestCommonAncestor(root, node2, node7).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (root.equals(p) || root.equals(q)) {
            return  root;
        }

        if(left == null || right == null) {
            return left == null ? right : left;
        }

        return root;
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