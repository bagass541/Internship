public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        int[][] matrix2 = new int[][] {{1, 3}};
        System.out.println(searchMatrix(matrix2, 1));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[i].length - 1;

        int m = matrix.length;

        while (i < m  && j >= 0 && target != matrix[i][j]) {
            if (matrix[i][j] > target && j > 0) {
                j--;
            } else if (i < m - 1) {
                i++;
            } else {
                break;
            }
        }

        return target == matrix[i][j];
    }
}