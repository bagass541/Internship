import com.sun.source.tree.Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2147483648);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2147483647);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(7);

   //     root.left = node2;
        root.right = node3;

//        node3.left = node4;
//        node3.right = node5;

        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidHelper(root.left, Long.MIN_VALUE, root.val) &&
                isValidHelper(root.right, root.val, Long.MAX_VALUE);
    }

    public static boolean isValidHelper(TreeNode root, long min, long max) {
        if(root == null) {
            return true;
        }

       if(root.val <= min || root.val >= max) {
           return false;
       }


        return isValidHelper(root.left, min, root.val) &&
                isValidHelper(root.right, root.val, max);
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