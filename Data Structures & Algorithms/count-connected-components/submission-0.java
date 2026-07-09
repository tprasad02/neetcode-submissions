class Solution {
    public int countComponents(int n, int[][] edges) {
        // parent[i] = parent of node i in the disjoint set tree
        int[] parent = new int[n];
        // rank[i] = approximate height of the tree rooted at i
        int[] rank = new int[n];

        // Initially, every node is its own component
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Start with n separate connected components
        int components = n;

        // Process each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // If u and v were in different components,
            // union them and reduce our component count
            if (union(u, v, parent, rank)) {
                components--;
            }
        }
        return components;
    }

    private int find(int x, int[] parent) {
        // If x is the root, we've found the representative.
        if (parent[x] == x) {
            return x;
        }
        // Path compression:
        // make every node on the path point directly to the root
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    private boolean union(int x, int y,
                          int[] parent,
                          int[] rank) {
        // Find the representative of each component
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        // Already in the same component
        if (rootX == rootY) {
            return false;
        }

        // Union by rank:
        // attach the shorter tree underneath the taller tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // Same height, pick one root arbitrarily
            parent[rootY] = rootX;
            // Height increases by one
            rank[rootX]++;
        }
        return true;
    }
}