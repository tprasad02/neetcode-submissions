class Solution {
    // Time: O(V + E), Space: O(V + E)
    // Topological Sort using indegrees

    // Stores the final topological ordering of the courses
    private List<Integer> output = new ArrayList<>();
    // indegree[i] = number of prerequisites course i still has
    private int[] indegree;
    // course -> courses that depend on it
    private List<List<Integer>> adj;
    // Prevent processing the same course multiple times
    private boolean[] visited;

    private void dfs(int node) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        output.add(node);
        // "Take" this course by removing it as a prerequisite
        // from all of its neighboring courses
        for (int nei : adj.get(node)) {
            indegree[nei]--;
            // If a neighboring course has no remaining prerequisites,
            // it is now ready to be taken
            if (indegree[nei] == 0) {
                dfs(nei);
            }
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Initialize the adjacency list
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        indegree = new int[numCourses];
        visited = new boolean[numCourses];
        // Build the graph
        // prerequisite -> course
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
            adj.get(pre[1]).add(pre[0]);
        }
        // Start DFS from every course with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                dfs(i);
            }
        }
        // If we couldn't process every course,
        // there must be a cycle
        if (output.size() != numCourses) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = output.get(i);
        }
        return res;
    }
}