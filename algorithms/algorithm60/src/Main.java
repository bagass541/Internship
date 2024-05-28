public class Main {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};

        char[][] board2 = new char[][]{
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};

        String word = "AAB";

        System.out.println(exist(board2, word));
    }

    public static boolean exist(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == wordArray[0]) {
                    if (exist(wordArray, i, j, board, 0)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    private static boolean exist(char[] wordArray, int i, int j, char[][] board, int letters) {
        if (letters == wordArray.length) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                letters >= wordArray.length || board[i][j] != wordArray[letters]) {

            return false;
        }

        board[i][j] ^= 256;

        boolean exist = exist(wordArray, i, j - 1, board, letters + 1) ||
                exist(wordArray, i, j + 1, board, letters + 1) ||
                exist(wordArray, i - 1, j, board, letters + 1) ||
                exist(wordArray, i + 1, j, board, letters + 1);

        board[i][j] ^= 256;

        return exist;
    }
}