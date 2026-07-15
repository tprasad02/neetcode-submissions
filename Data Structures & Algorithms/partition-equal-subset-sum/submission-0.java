public class Solution {
    public boolean canPartition(int[] nums) {
        // Time: O(n*t), t is sum of array elements / 2
        // Space: O(t)

        // Calculate total sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // Cannot split an odd sum into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        // dp[j] means:
        // "Using numbers seen so far, can I make a subset with sum j?"
        boolean[] dp = new boolean[target + 1];

        // We can always make sum 0 by choosing nothing
        dp[0] = true;

        // Try including each number
        for (int num : nums) {
            // Go backwards so we don't reuse the same number twice
            for (int j = target; j >= num; j--) {
                // Exclude num:
                // dp[j] stays whatever it was
                // Include num:
                // if we could make j - num before,
                // then adding num makes j possible
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}