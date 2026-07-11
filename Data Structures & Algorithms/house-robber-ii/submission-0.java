public class Solution {
    public int rob(int[] nums) {
        // Only one house
        if (nums.length == 1) return nums[0];

        // Since the houses are in a circle, we can't rob
        // both the first and last house
        // So solve:
        // 1. Houses [1...n-1]
        // 2. Houses [0...n-2]
        return Math.max(helper(Arrays.copyOfRange(nums, 1, nums.length)),
                        helper(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    private int helper(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i] = max money from houses 0...i
        int[] dp = new int[nums.length];

        // Only one house to choose from
        dp[0] = nums[0];

        // Pick the richer of the first two houses
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // Two choices:
            // 1. Skip house i  -> dp[i - 1]
            // 2. Rob house i   -> dp[i - 2] + nums[i]
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // Max money from all houses in this range
        return dp[nums.length - 1];
    }
}