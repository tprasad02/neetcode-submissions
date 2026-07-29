public class Solution {
    public int uniquePaths(int m, int n) {
        // O(m*n) time, O(n) space
        
        // Initialize last row with 1 path per cell
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // Process rows from bottom to top
        for (int i = m - 2; i >= 0; i--) {
            // Process columns from right to left
            for (int j = n - 2; j >= 0; j--) {
                // Paths from below + paths from the right
                dp[j] += dp[j + 1];
            }
        }

        // Total paths from the top-left
        return dp[0];
    }
}