class Solution {
    public boolean exist(char[][] board, String word) {
        //O(m * (4^n)) time, O(n) space
        // Try starting the word from every cell in the board
        // Slightly similar to Number of Islands (graph problem)
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word,
                        int r, int c, int i) {
        // Invariant:
        // We've already matched the first i characters of the word.
        // This call asks whether we can match word[i...] starting
        // from cell (r, c)

        // Base case: we've matched every character in the word
        if (i == word.length()) {
            return true;
        }
        // Stop if we're outside the board
        if (r < 0 || c < 0 ||
            r >= board.length || c >= board[0].length) {
            return false;
        }
        // Stop if this cell doesn't match the next character
        // or has already been visited
        if (board[r][c] != word.charAt(i)) {
            return false;
        }
        // Mark this cell as visited so it can't be reused
        char temp = board[r][c];
        board[r][c] = '#';
        // Try extending the path in all four directions
        boolean found =
            dfs(board, word, r + 1, c, i + 1) || // down
            dfs(board, word, r - 1, c, i + 1) || // up
            dfs(board, word, r, c + 1, i + 1) || // right
            dfs(board, word, r, c - 1, i + 1);   // left
        // Backtrack: restore the cell so it can be used
        // in a different search path
        board[r][c] = temp;
        return found;
    }
}