class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Time: O(ElogV)
        // Space: O(V+E)
        // Single-source shortest path:
        // k is the source, and we need the shortest time to every node
        // Edge weights are positive (time), so use Dijkstra's
        
        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph[u].add(new int[]{v, w});
        }
        // dist[i] = shortest time from k to node i
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min-heap: {node, distance}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );
        pq.offer(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int node = current[0];
            int currDist = current[1];
            // Skip outdated entry
            if (currDist > dist[node]) {
                continue;
            }
            // Explore neighbors
            for (int[] edge : graph[node]) {
                int neighbor = edge[0];
                int weight = edge[1];
                int newDist = currDist + weight;
                // Relax edge
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.offer(new int[]{neighbor, newDist});
                }
            }
        }

        // Signal must reach the last node, so take max shortest distance
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }
}