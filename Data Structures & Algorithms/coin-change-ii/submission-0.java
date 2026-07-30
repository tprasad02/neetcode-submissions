public class Solution {
    public int change(int amount, int[] coins) {
        // O(na) time, O(a) space
        // n is number of coins, a is target amount
        
        // dp[a] = number of ways to form amount a
        int[] dp = new int[amount + 1];

        // One way to form amount 0
        dp[0] = 1;

        // Process each coin
        for (int i = coins.length - 1; i >= 0; i--) {

            // Update all amounts using the current coin
            for (int a = 1; a <= amount; a++) {

                // Add ways that include the current coin
                if (coins[i] <= a) {
                    dp[a] += dp[a - coins[i]];
                }
            }
        }

        // Total combinations to form the target amount
        return dp[amount];
    }
}