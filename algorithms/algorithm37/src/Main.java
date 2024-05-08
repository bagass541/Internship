public class Main {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        root.left = node2;
        root.right= node3;

        node2.left = node4;
        node2.right = node5;

   //     node3.left = node6;
        node3.right = node7;

        connect(root);

        System.out.println(root.left.next.val);
        System.out.println(root.left.left.next.val);
        System.out.println(root.left.right.next.val);
    }

    public static Node connect(Node root) {
        Node dummyNode = new Node(0);
        Node pre = dummyNode;
        Node dummyRoot = root;

        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }

            root = root.next;
            if (root == null) {
                pre = dummyNode;
                root = dummyNode.next;
                dummyNode.next = null;
            }
        }

        return dummyRoot;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}