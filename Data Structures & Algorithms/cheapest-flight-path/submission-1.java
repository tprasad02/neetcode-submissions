class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Create an adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Add each flight to the adjacency list
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            adj.get(from).add(new int[]{to, price});
        }

        // Use a priority queue to greedily choose the cheapest edge
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );

        // Add the source node with cost 0
        pq.offer(new int[]{src, 0, 0});
        while (!pq.isEmpty()) {
            
            // Get the cheapest path so far
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];
            int stops = curr[2];
            
            // If we reached the destination, return the cost
            if (node == dst) {
                return cost;
            }
            
            // If we exceeded k stops, backtrack
            if (stops > k) {
                continue;
            }
            
            // Try all possible next flights
            for (int[] flight : adj.get(node)) {
                int nextNode = flight[0];
                int price = flight[1];
                // Add the next path to the queue
                pq.offer(new int[]{
                    nextNode,
                    cost + price,
                    stops + 1
                });
            }
        }
        
        // If no path exists within k stops, return -1
        return -1;
    }
}