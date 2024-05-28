import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);

        root.left = node2;
        root.right = node3;

        node2.right = node4;

        System.out.println(kthSmallest(root, 3));
    }

    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        while(k != 0) {
            TreeNode currNode = stack.pop();
            k--;

            if(k == 0) {
                return currNode.val;
            }

            TreeNode right = currNode.right;
            while(right != null) {
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
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