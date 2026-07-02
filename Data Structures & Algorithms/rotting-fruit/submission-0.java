class Solution {
    // Time: O(m * n)
    // Space: O(m * n)
    // Uses multi-source BFS starting from every rotten orange
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        // Add every rotten orange to the queue
        // Count the number of fresh oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                }
                if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        // No fresh oranges to rot
        if (fresh == 0) {
            return 0;
        }
        int minutes = 0;
        // Four possible directions
        int[][] dirs = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };
        // Process one "minute" at a time
        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();
            // Process all oranges that are rotten at the current minute
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];
                // Try to rot each neighboring orange
                for (int[] dir : dirs) {
                    int r = row + dir[0];
                    int c = col + dir[1];
                    // Skip if out of bounds or not a fresh orange
                    if (r < 0 || c < 0 ||
                        r >= rows || c >= cols ||
                        grid[r][c] != 1) {
                        continue;
                    }
                    // Rot the fresh orange
                    grid[r][c] = 2;
                    fresh--;
                    // It will spread rot next minute
                    q.offer(new int[]{r, c});
                }
            }
            // One minute has passed
            minutes++;
        }
        // If fresh oranges remain, they could not be reached
        if (fresh > 0) {
            return -1;
        }
        return minutes;
    }
}