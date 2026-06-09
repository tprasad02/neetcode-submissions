public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Time complexity is O(logm + logn) = O(log(m*n))
        int rows = matrix.length;
        int cols = matrix[0].length;

        // First binary search identifies the only row
        // that could contain the target
        int top = 0, bot = rows - 1;
        while (top <= bot) {
            int row = (top + bot) / 2;
            if (target > matrix[row][cols - 1]) {
                top = row + 1;
            } else if (target < matrix[row][0]) {
                bot = row - 1;
            } else {
                break;
            }
        }

        // Target is outside the range of every row
        if (top > bot) {
            return false;
        }

        // Binary search within the candidate row
        int row = (top + bot) / 2;
        int l = 0, r = cols - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (target > matrix[row][m]) {
                l = m + 1;
            } else if (target < matrix[row][m]) {
                r = m - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}