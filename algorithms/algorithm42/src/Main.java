import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

        levelOrder(root)
               .forEach(list -> list.forEach(System.out::println));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> list = new ArrayList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int qLen = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < qLen; i++) {
                TreeNode currNode = queue.remove();
                level.add(currNode.val);

                if(currNode.left != null) {
                    queue.add(currNode.left);
                }

                if(currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            if(!level.isEmpty()) {
                list.add(level);
            }
        }

        return list;
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