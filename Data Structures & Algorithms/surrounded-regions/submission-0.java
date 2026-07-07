class Solution {
    public void solve(char[][] board) {
        // O(m*n) time, O(m*n) space
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        // Go through every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find an unvisited O, perform DFS
                if (board[i][j] == 'O' && !visited[i][j]) {
                    List<int[]> region = new ArrayList<>();
                    dfs(board, visited, region, i, j);
                    boolean surround = true;
                    // Check if any coordinate is on the perimeter
                    for (int[] cell : region) {
                        int x = cell[0];
                        int y = cell[1];
                        if (x == 0 || y == 0 || x == rows - 1 || y == cols - 1) {
                            surround = false;
                        }
                    }
                    // If not touching perimeter, convert everything to X
                    if (surround) {
                        for (int[] cell : region) {
                            board[cell[0]][cell[1]] = 'X';
                        }
                    }
                }
            }
        }
    }

    public void dfs(char[][] board, boolean[][] visited,
                    List<int[]> region, int row, int col) {
        int rows = board.length;
        int cols = board[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        if (visited[row][col] || board[row][col] == 'X') {
            return;
        }
        visited[row][col] = true;
        region.add(new int[]{row, col});
        // Traverse in all four directions
        dfs(board, visited, region, row + 1, col);
        dfs(board, visited, region, row - 1, col);
        dfs(board, visited, region, row, col + 1);
        dfs(board, visited, region, row, col - 1);
    }
}