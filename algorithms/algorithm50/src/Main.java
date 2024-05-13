// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        solve(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                solveHelper(board, 0, j);
            }

            if (board[m - 1][j] == 'O') {
                solveHelper(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') {
                solveHelper(board, i, n - 1);
            }
            if (board[i][0] == 'O') {
                solveHelper(board, i, 0);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void solveHelper(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'T';

        solveHelper(board, i + 1, j);
        solveHelper(board, i - 1, j);
        solveHelper(board, i, j + 1);
        solveHelper(board, i, j - 1);
    }
}
