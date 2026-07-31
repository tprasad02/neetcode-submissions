class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // dp[sum] = number of ways to reach sum with the numbers we have seen so far
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);

        for (int num : nums) {
            Map<Integer, Integer> next = new HashMap<>();
            for (int sum : dp.keySet()) {
                // Consider adding the current number
                next.put(sum + num, next.getOrDefault(sum + num, 0) + dp.get(sum));

                // Consider subtracting the current number
                next.put(sum - num, next.getOrDefault(sum - num, 0) + dp.get(sum));
            }

            // Keep the number of ways to reach each sum with the numbers seen so far
            dp = next;
        }

        return dp.getOrDefault(target, 0);
    }
}