public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node2;
        root.right = node5;

        root.left.left = node3;
        root.left.right = node4;

        root.right.right = node6;

        flatten(root);

        System.out.println(root.right.val);
        System.out.println(root.right.right.val);
        System.out.println(root.right.right.right.val);
        System.out.println(root.right.right.right.right.val);
    }

    public static void flatten(TreeNode root) {
        flattenHelper(root);
    }

    private static TreeNode flattenHelper(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftTail = flattenHelper(root.left);
        TreeNode rightTail = flattenHelper(root.right);

        if(root.left != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if(rightTail == null) {
            return leftTail == null ? root : leftTail;
        }

        return rightTail;
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