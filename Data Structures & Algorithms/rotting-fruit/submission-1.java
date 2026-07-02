class Solution {
    // Time: O(m * n)
    // Space: O(m * n)
    // Uses multi-source BFS starting from every rotten orange
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;

        // Add every rotten orange to the queue
        // Count the number of fresh oranges
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new int[] { row, col });
                }
                if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }
        // No fresh oranges to rot
        if (fresh == 0) {
            return 0;
        }
        int minutes = 0;
        // Each level of the BFS represents one minute
        while (!queue.isEmpty() && fresh > 0) {
            int rottenThisMinute = queue.size();
            // Process every orange that is rotten this minute
            for (int i = 0; i < rottenThisMinute; i++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                // Try to rot each neighboring orange
                fresh -= rot(grid, row - 1, col, queue); // up
                fresh -= rot(grid, row + 1, col, queue); // down
                fresh -= rot(grid, row, col - 1, queue); // left
                fresh -= rot(grid, row, col + 1, queue); // right
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

    // Rots a neighboring orange if possible
    // Returns 1 if a fresh orange became rotten, otherwise 0
    private int rot(int[][] grid, int row, int col, Queue<int[]> queue) {
        // Outside the grid
        if (row < 0 || col < 0 ||
            row >= grid.length || col >= grid[0].length) {
            return 0;
        }
        // Not a fresh orange
        if (grid[row][col] != 1) {
            return 0;
        }
        // Rot the orange
        grid[row][col] = 2;
        // It will spread rot next minute
        queue.offer(new int[] { row, col });
        return 1;
    }
}