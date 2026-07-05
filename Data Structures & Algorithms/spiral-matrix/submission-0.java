class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Time: O(m * n), where m is num of rows and n is num of columns
        // Space: O(1) extra space (excluding the output list)

        List<Integer> result = new ArrayList<>();
        // Initialize the four boundaries of the current layer
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        // Continue while there are still rows and columns to process
        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;
            // Traverse from top to bottom along the right column
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;
            // Traverse from right to left along the bottom row,
            // if there is still an unvisited row
            // Needs to be checked in case there was only one row
            // to begin with
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            // Traverse from bottom to top along the left column,
            // if there is still an unvisited column
            // Needs to be checked in case there was only one column
            // to begin with
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        return result;
    }
}