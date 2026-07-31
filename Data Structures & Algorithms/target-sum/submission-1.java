public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        // dp[i] stores how many ways we can reach each total
        // using the first i numbers
        Map<Integer, Integer>[] dp = new HashMap[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = new HashMap<>();
        }

        // With no numbers, there is one way to reach a total of 0
        dp[0].put(0, 1);

        for (int i = 0; i < n; i++) {
            // Each subproblem represents a total reached
            // using the numbers we have seen so far
            for (Map.Entry<Integer, Integer> entry : dp[i].entrySet()) {
                int total = entry.getKey();
                int count = entry.getValue();

                // We can move away from the target by adding
                // the current number and reach the target later
                dp[i + 1].put(total + nums[i],
                          dp[i + 1].getOrDefault(total + nums[i], 0) + count);

                // We can also subtract the current number
                dp[i + 1].put(total - nums[i],
                          dp[i + 1].getOrDefault(total - nums[i], 0) + count);
            }
        }

        // After using all numbers, return the number of ways
        // to reach the target
        return dp[n].getOrDefault(target, 0);
    }
}