import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        root.left = node2;
        root.right = node3;
        node2.left = node4;

        System.out.println(rightSideView(root));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int qLen = queue.size();
            TreeNode curr = null;
            for (int i = 0; i < qLen; i++) {
                curr = queue.remove();

                if (curr.left != null) {
                    queue.add(curr.left);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }

            list.add(curr.val);
        }

        return list;
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