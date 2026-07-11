public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // O(n) time, O(n) space
        
        int n = cost.length;
        // dp[i] = min cost to reach step i
        int[] dp = new int[n + 1];

        // Build the answer from step 2 to the top
        for (int i = 2; i <= n; i++) {
            // Reach i from i-1 or i-2
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }
        // Min cost to reach the top
        return dp[n];
    }
}