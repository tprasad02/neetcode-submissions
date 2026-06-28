class Solution {
    public int numIslands(char[][] grid) {
        // O(m*n) time, O(m*n) space
        int islands = 0;
        // Visit every cell in the grid
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // Found a new island
                if (grid[r][c] == '1') {
                    islands++;
                    // Flood-fill the entire island so it isn't counted again
                    dfs(grid, r, c);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        // Stop if we're outside the grid
        if (r < 0 || c < 0 ||
            r >= grid.length || c >= grid[0].length) {
            return;
        }
        // Stop if this cell is water or has already been visited
        if (grid[r][c] == '0') {
            return;
        }
        // Mark this piece of land as visited
        grid[r][c] = '0';
        // Explore all four directions
        dfs(grid, r + 1, c); // down
        dfs(grid, r - 1, c); // up
        dfs(grid, r, c + 1); // right
        dfs(grid, r, c - 1); // left
    }
}