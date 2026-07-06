class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Traverse every cell and ask:
        // Can water from this cell reach the Pacific?
        // Can water from this cell reach the Atlantic?
        List<List<Integer>> ans = new ArrayList<>();
        int rows = heights.length;
        int cols = heights[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boolean[][] pacificVisited = new boolean[rows][cols];
                boolean[][] atlanticVisited = new boolean[rows][cols];
                boolean reachesPacific =
                        dfs(r, c, heights, pacificVisited, true);
                boolean reachesAtlantic =
                        dfs(r, c, heights, atlanticVisited, false);
                if (reachesPacific && reachesAtlantic) {
                    ans.add(Arrays.asList(r, c));
                }
            }
        }
        return ans;
    }

    // Returns true if this path can reach the requested ocean
    // pacific == true  -> top row or left column
    // pacific == false -> bottom row or right column
    private boolean dfs(int row, int col,
                        int[][] heights,
                        boolean[][] visited,
                        boolean pacific) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Stop if we've reached the desired ocean
        if (pacific) {
            if (row == 0 || col == 0) {
                return true;
            }
        } else {
            if (row == rows - 1 || col == cols - 1) {
                return true;
            }
        }
        visited[row][col] = true;
        int currHeight = heights[row][col];
        // Up
        if (row - 1 >= 0 &&
            !visited[row - 1][col] &&
            heights[row - 1][col] <= currHeight) {
            if (dfs(row - 1, col, heights, visited, pacific)) {
                return true;
            }
        }
        // Down
        if (row + 1 < rows &&
            !visited[row + 1][col] &&
            heights[row + 1][col] <= currHeight) {
            if (dfs(row + 1, col, heights, visited, pacific)) {
                return true;
            }
        }
        // Left
        if (col - 1 >= 0 &&
            !visited[row][col - 1] &&
            heights[row][col - 1] <= currHeight) {
            if (dfs(row, col - 1, heights, visited, pacific)) {
                return true;
            }
        }
        // Right
        if (col + 1 < cols &&
            !visited[row][col + 1] &&
            heights[row][col + 1] <= currHeight) {
            if (dfs(row, col + 1, heights, visited, pacific)) {
                return true;
            }
        }
        return false;
    }
}