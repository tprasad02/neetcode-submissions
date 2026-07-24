public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Initialize distances
        int INF = Integer.MAX_VALUE;
        List<int[]>[] adj = new ArrayList[n];
        int[][] dist = new int[n][k + 5];

        for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
        // Build adjacency list
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] flight : flights) {
            adj[flight[0]].add(new int[]{flight[1], flight[2]});
        }
        // Start at source with cost 0
        dist[src][0] = 0;
        // Min heap stores {cost, node, stops}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        minHeap.offer(new int[]{0, src, -1});

        while (!minHeap.isEmpty()) {
            // Get cheapest path
            int[] top = minHeap.poll();
            int cst = top[0], node = top[1], stops = top[2];
            // Reached destination
            if (node == dst) return cst;
            // Stop if limit reached or path is more expensive
            if (stops == k || dist[node][stops + 1] < cst) continue;

            // Check neighboring flights
            for (int[] neighbor : adj[node]) {
                int nei = neighbor[0], w = neighbor[1];
                // Calculate next cost and stops
                int nextCst = cst + w;
                int nextStops = stops + 1;
                // Update if cheaper
                if (dist[nei][nextStops + 1] > nextCst) {
                    dist[nei][nextStops + 1] = nextCst;
                    minHeap.offer(new int[]{nextCst, nei, nextStops});
                }
            }
        }

        // No valid path
        return -1;
    }
}