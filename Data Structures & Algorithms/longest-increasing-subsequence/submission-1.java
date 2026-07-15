class Solution {
    public int lengthOfLIS(int[] nums) {
        // Time: O(n^2)
        // Space: O(n^2)

        int n = nums.length;
        // dp[i][j+1] = LIS starting from index i,
        // where j is the index of the previous chosen element
        int[][] dp = new int[n + 1][n + 1];

        // Fill backwards because dp[i] depends on dp[i+1]
        for (int i = n - 1; i >= 0; i--) {
            // j is the previous chosen index
            for (int j = i - 1; j >= -1; j--) {
                // Option 1: skip nums[i]
                int LIS = dp[i + 1][j + 1];
                // Option 2: take nums[i] if it increases the sequence
                if (j == -1 || nums[j] < nums[i]) {
                    LIS = Math.max(LIS, 1 + dp[i + 1][i + 1]);
                }
                dp[i][j + 1] = LIS;
            }
        }
        // Start at index 0 with no previous element chosen
        return dp[0][0];
    }
}