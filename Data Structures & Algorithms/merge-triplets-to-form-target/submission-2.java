class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // Time: O(n)
        // Space: O(1)
        // Note: we can choose any subset of triplets and 
        // merge them by taking the coordinate-wise maximum
        
        Set<Integer> found = new HashSet<>();
        for (int[] t : triplets) {
            // Ignore triplets that exceed the target
            // We cannot decrease values after taking max
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                continue;
            }
            // Check each coordinate of this triplet
            for (int i = 0; i < t.length; i++) {
                // This triplet can provide this coordinate of the target
                // We only need one valid triplet for each coordinate
                if (t[i] == target[i]) {
                    found.add(i);
                }
            }
        }
        // If we found triplets that can provide all 3 coordinates, we can merge to target
        return found.size() == 3;
    }
}