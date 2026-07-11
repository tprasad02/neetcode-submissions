class Solution {
    public int rob(int[] nums) {
        // O(n) time, O(n) space
    
        int n = nums.length;
        if (n == 1) return nums[0];

        // dp[i] = max money we can rob from houses 0...i
        int[] dp = new int[n];

        // Only one house to choose from
        dp[0] = nums[0];

        // Pick the richer of the first two houses
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // Two choices:
            // 1. Skip house i  -> dp[i - 1]
            // 2. Rob house i   -> dp[i - 2] + nums[i]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // Max money from all houses
        return dp[n - 1];
    }
}