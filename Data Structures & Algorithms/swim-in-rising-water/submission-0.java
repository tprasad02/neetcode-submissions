class Solution {
    public int swimInWater(int[][] grid) {
        // Time: O(n^2 logn)
        // Space: O(n^2)
        // Shortest path problem:
        // Use Dijkstra's algorithm, but instead of adding weights,
        // the cost is max(current path cost, next cell's elevation)

        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // {row, col, maxElevationSoFar}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );
        dist[0][0] = grid[0][0];
        pq.offer(new int[]{0, 0, grid[0][0]});
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int maxElevation = curr[2];
            // Reached the destination with the minimum possible
            // maximum elevation along the path
            if (r == n - 1 && c == n - 1) {
                return maxElevation;
            }
            // Skip outdated entry
            if (maxElevation > dist[r][c]) {
                continue;
            }
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }
                // Path cost is the maximum elevation seen so far
                int newCost = Math.max(maxElevation, grid[nr][nc]);
                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.offer(new int[]{nr, nc, newCost});
                }
            }
        }
        return -1;
    }
}