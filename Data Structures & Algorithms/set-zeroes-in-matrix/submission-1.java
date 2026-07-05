class Solution {
    public void setZeroes(int[][] matrix) {
        // Time: O(m * n)
        // Space: O(m + n)

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Store which rows and columns should be zeroed
        HashMap<Integer, Boolean> zeroRows = new HashMap<>();
        HashMap<Integer, Boolean> zeroCols = new HashMap<>();

        // Traverse every row
        for (int r = 0; r < rows; r++) {
            // Tracks whether this row should be zeroed
            boolean rowHasZero = false;
            // Check every element in the row
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    // Once true, it stays true
                    rowHasZero = true;
                    // This column also needs to be zeroed
                    zeroCols.put(c, true);
                }
            }
            // If this row contained a zero, mark it
            if (rowHasZero) {
                zeroRows.put(r, true);
            }
        }

        // Zero out every marked row and column
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (zeroRows.containsKey(r) || zeroCols.containsKey(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}