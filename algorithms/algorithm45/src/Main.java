import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        root.left = node2;
        root.right = node3;

        node3.left = node4;
        node3.right = node5;

        System.out.println(zigzagLevelOrder(root));

    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        boolean isNeededToReverse = false;

        queue.add(root);

        while (!queue.isEmpty()) {
            int qLen = queue.size();

            List<Integer> elements = new ArrayList<>();
            for (int i = 0; i < qLen; i++) {
                TreeNode curr = queue.remove();

                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }

                elements.add(curr.val);
            }

            if (isNeededToReverse) {
                Collections.reverse(elements);
                isNeededToReverse = false;
            } else {
                isNeededToReverse = true;
            }

            result.add(elements);
        }

        return result;
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