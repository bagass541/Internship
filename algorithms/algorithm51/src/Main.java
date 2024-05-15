import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        node3.neighbors.add(node4);
        node3.neighbors.add(node2);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        Node clone = cloneGraph(node1);

        System.out.println(clone.val);
        System.out.println(clone.neighbors.get(0).val);
        System.out.println(clone.neighbors.get(1).val);
    }

    private static Map<Integer, Node> map = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node copyNode = new Node(node.val);
        map.put(copyNode.val, copyNode);

        for (Node neighbor : node.neighbors) {
            copyNode.neighbors.add(cloneGraph(neighbor));
        }

        return copyNode;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}