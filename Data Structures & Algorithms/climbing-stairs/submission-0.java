public class Solution {
    // Time: O(n), Space: O(n)
    // Non-DP, recursive solution is O(2^n), similar to Fibonacci
    public int climbStairs(int n) {
        // Base cases:
        // 1 stair  -> 1 way  (1)
        // 2 stairs -> 2 ways (1+1, 2)
        if (n <= 2) {
            return n;
        }
        // dp[i] will store the number of ways
        // to reach the i-th stair
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // To reach stair i, your last move was either:
        // 1-step from stair i - 1, or
        // 2-step from stair i - 2
        // Therefore:
        // dp[i] = dp[i - 1] + dp[i - 2]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}