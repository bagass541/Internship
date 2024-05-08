import com.sun.source.tree.Tree;

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

        averageOfLevels(root).forEach(System.out::println);
    }

    public static List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Double> list = new ArrayList<>();

        while(!queue.isEmpty()) {
            int qLen = queue.size();
            double avg = 0;

            for (int i = 0; i < qLen; i++) {
                TreeNode currNode = queue.remove();
                avg += currNode.val;

                if(currNode.left != null) {
                    queue.add(currNode.left);

                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            list.add(avg / qLen);
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