class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        // Visit every cell
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // Found an unvisited island
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }

    // Returns the area of the island starting at (r, c)
    private int dfs(int[][] grid, int r, int c) {
        // Out of bounds
        if (r < 0 || c < 0 ||
            r >= grid.length || c >= grid[0].length) {
            return 0;
        }
        // Water or already visited
        if (grid[r][c] == 0) {
            return 0;
        }
        // Mark as visited
        grid[r][c] = 0;
        // Current cell + four directions
        return 1
            + dfs(grid, r + 1, c)
            + dfs(grid, r - 1, c)
            + dfs(grid, r, c + 1)
            + dfs(grid, r, c - 1);
    }
}