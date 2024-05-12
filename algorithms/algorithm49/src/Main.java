// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        char[][] grid2 = new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'} 
        };
        
        char[][] grid3 = new char[][] {
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        };

        char[][] grid4 = new char[][] {
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };

        System.out.println(numIslands(grid4));
    }

    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    markNeighbours(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void markNeighbours(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != '1')  {
            return;
        }

        grid[i][j] = '2';

        markNeighbours(grid, i + 1, j);
        markNeighbours(grid, i - 1, j);
        markNeighbours(grid, i, j + 1);
        markNeighbours(grid, i, j - 1);
    }
}