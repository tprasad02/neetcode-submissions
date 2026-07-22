public class Solution {
    public int minCostConnectPoints(int[][] points) {
        // Time: O(n^2)
        // Space: O(n)
        // This is a Minimum Spanning Tree (MST) problem
        // We use Prim's algorithm to connect all points with minimum cost
        // Cost between two points = Manhattan distance
        
        int n = points.length, node = 0;
        int[] dist = new int[n];
        boolean[] visit = new boolean[n];
        Arrays.fill(dist, 100000000);
        int edges = 0, res = 0;
        // MST needs exactly n - 1 edges to connect n nodes
        while (edges < n - 1) {
            // Add current node to the MST
            visit[node] = true;
            int nextNode = -1;
            for (int i = 0; i < n; i++) {
                if (visit[i]) continue;
                // Calculate Manhattan distance between current node and i
                int curDist = Math.abs(points[i][0] - points[node][0]) +
                              Math.abs(points[i][1] - points[node][1]);
                // Keep the cheapest edge connecting i to any node in the MST
                dist[i] = Math.min(dist[i], curDist);
                // Choose the unvisited node with the smallest connection cost
                if (nextNode == -1 || dist[i] < dist[nextNode]) {
                    nextNode = i;
                }
            }
            // Add the cheapest edge to the MST
            res += dist[nextNode];
            // Move to the next node we selected
            node = nextNode;
            edges++;
        }
        return res;
    }
}