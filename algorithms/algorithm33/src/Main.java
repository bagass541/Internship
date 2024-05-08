import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setValue(1);

        Node node2 = new Node();
        node2.setValue(2);
        node1.setNext(node2);

        Node node3 = new Node();
        node3.setValue(3);
        node2.setNext(node3);

        Node node4 = new Node();
        node4.setValue(4);
        node3.setNext(node4);

        Node node5 = new Node();
        node5.setValue(5);
        node4.setNext(node5);

        Node node6 = new Node();
        node6.setValue(6);
        node5.setNext(node6);

        Node node7 = new Node();
        node7.setValue(7);
        node6.setNext(node7);

        Node node8 = new Node();
        node8.setValue(8);
        node7.setNext(node8);

        Node node9 = new Node();
        node9.setValue(9);
        node8.setNext(node9);

        Node node10 = new Node();
        node10.setValue(10);
        node9.setNext(node10);

        Node node11 = new Node();
        node11.setValue(11);
        node10.setNext(node11);

        Node node12 = new Node();
        node12.setValue(12);
        node11.setNext(node12);
        node12.setNext(node4);

        System.out.println(loopSize(node1));
    }

    public static int loopSize(Node node) {
        List<Node> nodes = new ArrayList<>();
        while(!nodes.contains(node)) {
            nodes.add(node);
            node = node.getNext();
        }
        return nodes.size() - nodes.indexOf(node);
    }

    static class Node {
        private Node next;

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}