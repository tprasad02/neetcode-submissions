class Solution {
    public List<List<String>> solveNQueens(int n) {
        // Since we place queens row by row from top to bottom, each recursive
        // call is responsible for placing the queen in exactly one row
        // For the current row, try placing the queen in every column
        // A position is valid if no previously placed queen attacks it

        // Although a queen attacks in 8 directions:
        //      ↖  ↑  ↗
        //      ←  Q  →
        //      ↙  ↓  ↘
        // we only need to check:
        //
        //      ↖  ↑  ↗
        //
        // because queens have only been placed in previous rows
        // There cannot be any queens below the current row yet

        // After placing a queen:
        // 1. Recurse to place a queen in the next row
        // 2. Backtrack by removing the queen
        // 3. Try the next column in the current row

        // Stores all valid board configurations
        List<List<String>> res = new ArrayList<>();
        // Build the board
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        // Tracks which columns already contain a queen
        Set<Integer> cols = new HashSet<>();
        // Tracks occupied diagonals (row - col)
        Set<Integer> diag1 = new HashSet<>();
        // Tracks occupied anti-diagonals (row + col)
        Set<Integer> diag2 = new HashSet<>();
        // Start placing queens from the first row
        dfs(0, board, cols, diag1, diag2, res);
        return res;
    }

    private void dfs(int row,
                     char[][] board,
                     Set<Integer> cols,
                     Set<Integer> diag1,
                     Set<Integer> diag2,
                     List<List<String>> res) {

        // Invariant:
        // We've successfully placed one queen in every row from 0 to row-1
        // without any queens attacking each other
        // This call chooses the column for the queen in the current row

        // Base case: every row has a queen
        if (row == board.length) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            res.add(solution);
            return;
        }

        // Try placing the queen in every column of this row
        for (int col = 0; col < board.length; col++) {
            // Skip if another queen attacks this position
            if (cols.contains(col) ||
                diag1.contains(row - col) ||
                diag2.contains(row + col)) {
                continue;
            }
            // Place the queen
            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);
            // Place a queen in the next row
            dfs(row + 1, board, cols, diag1, diag2, res);
            // Backtrack and try another column
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }
}