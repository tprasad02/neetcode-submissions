class Solution {
    public boolean isValidSudoku(char[][] board) {
        // O(n^2) time, O(n) space
        
        // Check rows
        for (int row = 0; row < 9; row++) {
            Set<Character> seen = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c != '.' && seen.contains(c)) {
                    return false;
                }
                seen.add(c);
            }
        }

        // Check columns
        for (int col = 0; col < 9; col++) {
            Set<Character> seen = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                char c = board[row][col];
                if (c != '.' && seen.contains(c)) {
                    return false;
                }
                seen.add(c);
            }
        }

        // Check 3x3 grids
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                Set<Character> seen = new HashSet<>();
                // top-left corner of this box
                int startRow = boxRow * 3;
                int startCol = boxCol * 3;
                for (int i = startRow; i < startRow + 3; i++) {
                    for (int j = startCol; j < startCol + 3; j++) {
                        char c = board[i][j];
                        if (c != '.' && seen.contains(c)) return false;
                        seen.add(c);
                    }
                }
            }
        }
        return true;
    }
}