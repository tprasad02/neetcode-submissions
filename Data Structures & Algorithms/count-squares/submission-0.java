class CountSquares {
    // Key idea: Pick a point in the same row-aligned
    // diagonal direction and reconstruct the square

    // x -> (y -> frequency)
    private Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    public CountSquares() {}

    public void add(int[] point) {
        int x = point[0], y = point[1];
        map.putIfAbsent(x, new HashMap<>());
        Map<Integer, Integer> col = map.get(x);
        col.put(y, col.getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
        int qx = point[0], qy = point[1];
        int res = 0;
        // Try every column that could form a diagonal square
        for (int x : map.keySet()) {
            if (x == qx) continue;
            int dy = x - qx; // Horizontal distance
            // Try square above and below
            res += helper(qx, qy, x, qy + dy); // Up-right square
            res += helper(qx, qy, x, qy - dy); // Down-right square
        }
        return res;
    }
    // Count ways to complete square given diagonal candidates
    private int helper(int qx, int qy, int x, int y) {
        if (!map.containsKey(x)) return 0;
        Map<Integer, Integer> col1 = map.getOrDefault(qx, new HashMap<>());
        Map<Integer, Integer> col2 = map.get(x);
        return col1.getOrDefault(y, 0) *
               col2.getOrDefault(qy, 0) *
               col2.getOrDefault(y, 0);
    }
}