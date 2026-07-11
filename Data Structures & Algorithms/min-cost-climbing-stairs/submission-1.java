public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // O(n) time, O(1) space

        // Build min cost from the end
        for (int i = cost.length - 3; i >= 0; i--) {
            // Current step + cheaper next jump
            cost[i] += Math.min(cost[i + 1], cost[i + 2]);
        }
        // Start at 0 or 1, whichever is cheaper
        return Math.min(cost[0], cost[1]);
    }
}