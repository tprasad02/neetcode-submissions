public class Solution {
    // A graph is a valid tree if:
    // 1. It is connected 
    // (every node can be reached from every other node)
    // 2. It has no cycles
    //
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)

    public boolean validTree(int n, int[][] edges) {
        // A tree with n nodes must have exactly n - 1 edges
        // If there are more edges, there must be a cycle
        if (edges.length > n - 1) {
            return false;
        }

        // Create an adjacency list to represent the undirected graph
        List<List<Integer>> adj = new ArrayList<>();
        
        // Initialize the list for every node
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build the graph
        // Since this is undirected, add both directions:
        // edge u - v means u connects to v and v connects to u
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Keep track of nodes we have already visited during DFS
        Set<Integer> visit = new HashSet<>();

        // Start DFS from node 0
        // If DFS detects a cycle, this graph cannot be a tree
        if (!dfs(0, -1, visit, adj)) {
            return false;
        }

        // If we did not visit every node, the graph is disconnected
        // A tree must have every node connected
        return visit.size() == n;
    }

    private boolean dfs(int node, int parent, Set<Integer> visit,
                        List<List<Integer>> adj) {

        // If we visit a node that was already visited,
        // we found a cycle
        if (visit.contains(node)) {
            return false;
        }

        // Mark the current node as visited
        visit.add(node);

        // Explore all neighboring nodes
        for (int nei : adj.get(node)) {
            // In an undirected graph, ignore the edge we came from
            // Example: 0 -> 1, then seeing 0 from node 1 is not a cycle
            if (nei == parent) {
                continue;
            }
            // If any neighbor creates a cycle, the graph is not a tree
            if (!dfs(nei, node, visit, adj)) {
                return false;
            }
        }
        return true;
    }
}