import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] inorder = new int[] {9,3,15,20,7};
        int[] postorder = new int[] {9,15,7,20,3};

        TreeNode root = buildTree(inorder, postorder);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
        System.out.println(root.right.right.val);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
           return null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int mid = 0;

        for (int i = 0; i < inorder.length; i++) {
            if(root.val == inorder[i]) {
                mid = i;
                break;
            }
        }

        root.left = buildTree(Arrays.copyOfRange(inorder, 0, mid),
                Arrays.copyOfRange(postorder, 0, mid));
        root.right = buildTree(Arrays.copyOfRange(inorder, mid + 1, inorder.length),
                Arrays.copyOfRange(postorder, mid, postorder.length - 1));

        return root;
    }

    public static class TreeNode {
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