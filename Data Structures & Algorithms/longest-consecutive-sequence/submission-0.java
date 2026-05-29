class Solution {
    // Union-Find structure:
    // parent[x] = parent pointer of value x
    Map<Integer, Integer> parent = new HashMap<>();

    // Helper: find root with path compression
    // Flattens structure for near O(1) amortized lookup
    private int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }
    
    public int longestConsecutive(int[] nums) {
        // Initialize each number as its own parent initially
        for (int num : nums) {
            parent.put(num, num);
        }
        // Union step:
        // If x and x+1 exist, merge them into the same set
        for (int num : nums) {
            if (parent.containsKey(num + 1)) {
                int root1 = find(num);
                int root2 = find(num + 1);
                if (root1 != root2) {
                    parent.put(root1, root2);
                }
            }
        }
        // Count set sizes
        Set<Integer> seen = new HashSet<>();
        Map<Integer, Integer> size = new HashMap<>();
        int max = 0;

        for (int num : nums) {
            if (seen.add(num)) { // ensures uniqueness
                int root = find(num);
                size.put(root, size.getOrDefault(root, 0) + 1);
                max = Math.max(max, size.get(root));
            }
        }
        return max;
    }
}