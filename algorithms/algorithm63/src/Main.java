public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }

    private static Node helper(int[][] grid, int x, int y, int length) {
        if (length == 1) {
            return new Node(grid[x][y] != 0, true, null, null, null, null);
        }

        Node result = new Node();
        Node topLeft = helper(grid, x, y, length / 2);
        Node topRight = helper(grid, x , y + length / 2, length / 2);
        Node bottomLeft = helper(grid, x + length / 2, y, length / 2);
        Node bottomRight = helper(grid, x + length / 2, y + length / 2, length / 2);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomRight.val
                && bottomRight.val == bottomLeft.val) {

            result.val = topLeft.val;
            result.isLeaf = true;
            return result;
        } else {
            result.topLeft = topLeft;
            result.topRight = topRight;
            result.bottomRight = bottomRight;
            result.bottomLeft = bottomLeft;
        }

        return result;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}