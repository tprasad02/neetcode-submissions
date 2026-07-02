public class Solution {
    // Time: O(m * n)
    // Space: O(m * n)
    // Uses multi-source BFS starting from every treasure (0)
    public void islandsAndTreasure(int[][] grid) {
        // Queue for BFS
        Queue<int[]> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;

        // Add every treasure to the queue as a starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[] { i, j });
                }
            }
        }
        // No treasures to expand from
        if (q.size() == 0) return;

        // Four possible directions: up, left, down, right
        int[][] dirs = {
            { -1, 0 },
            {  0, -1 },
            {  1, 0 },
            {  0, 1 }
        };

        // Process cells level by level
        while (!q.isEmpty()) {
            // Current cell
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];
            // Visit each neighboring cell
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                // Skip if out of bounds, a wall, a treasure,
                // or already assigned its shortest distance
                if (r < 0 || c < 0 ||
                    r >= m || c >= n ||
                    grid[r][c] != Integer.MAX_VALUE) {
                    continue;
                }
                // Add the neighbor to the queue
                q.add(new int[] { r, c });
                // Distance is one more than the current cell
                grid[r][c] = grid[row][col] + 1;
            }
        }
    }
}