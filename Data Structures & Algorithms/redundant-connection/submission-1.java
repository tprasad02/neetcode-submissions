class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // parent[i] stores the parent of node i
        int[] parent = new int[n + 1];

        // rank[i] helps keep the trees balanced
        int[] rank = new int[n + 1];

        // Initially, every node is its own component
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process each edge one by one
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If u and v already have the same root,
            // they are already connected
            // Adding this edge would create a cycle,
            // so this is the redundant edge
            if (find(u, parent) == find(v, parent)) {
                return edge;
            }
            // Otherwise, merge their components
            union(u, v, parent, rank);
        }
        // Problem guarantees an answer exists,
        // but Java requires a return statement
        return new int[0];
    }

    private int find(int x, int[] parent) {
        // x is the representative of its component
        if (parent[x] == x) {
            return x;
        }
        // Path compression:
        // point directly to the root to make
        // future finds nearly O(1)
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    private void union(int x,
                       int y,
                       int[] parent,
                       int[] rank) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        // Already in the same component
        if (rootX == rootY) {
            return;
        }
        // Union by rank:
        // attach the smaller tree under the larger one
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // Same height: pick one root arbitrarily
            parent[rootY] = rootX;
            // Height increases by one
            rank[rootX]++;
        }
    }
}