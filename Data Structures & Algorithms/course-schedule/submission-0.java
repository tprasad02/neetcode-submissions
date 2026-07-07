class Solution {
    // Time: O(V + E), Space: O(V + E)
    // Use cycle detection (DFS)
    // Alternative approach:
    // Topological Sort - repeatedly take courses with indegree 0
    // If all courses are processed, there is no cycle
    // Otherwise, a cycle exists
    
    // Maps each course to the list of its prerequisites
    private Map<Integer, List<Integer>> preMap = new HashMap<>();

    // Keeps track of the current DFS path
    // If we revisit a course already in this set, we found a cycle
    private Set<Integer> visiting = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize every course with an empty prerequisite list
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        // Build the adjacency list
        // course -> prerequisites
        for (int[] prereq : prerequisites) {
            preMap.get(prereq[0]).add(prereq[1]);
        }
        // Run DFS on every course
        // If any DFS finds a cycle, it is impossible to finish all courses
        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int crs) {
        // If the course is already in our current path,
        // we found a cycle
        if (visiting.contains(crs)) {
            return false;
        }
        // No prerequisites left means this course is already doable
        if (preMap.get(crs).isEmpty()) {
            return true;
        }
        // Add this course to the current DFS path
        visiting.add(crs);
        // Visit all prerequisites
        for (int pre : preMap.get(crs)) {
            if (!dfs(pre)) {
                return false;
            }
        }
        // Finished exploring this branch, remove from current path
        visiting.remove(crs);
        // As the DFS snakes back up, we mark this course as doable
        // by clearing its prerequisite list so future DFS calls
        // can immediately return true
        preMap.put(crs, new ArrayList<>());
        return true;
    }
}