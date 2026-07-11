class Solution {
    public int coinChange(int[] coins, int amount) {
        // O(n*t) time, O(t) space
                
        // dp[x] = minimum number of coins needed to make amount x
        int[] dp = new int[amount + 1];

        // Start by assuming every amount is impossible
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // Build answers for smaller amounts first
        for (int x = 1; x <= amount; x++) {
            // Try every coin as the last coin used
            for (int coin : coins) {
                // We can only use this coin if
                // the remaining amount is not negative
                if (x - coin >= 0) {
                    // If coin is the last coin,
                    // then we need:
                    // (min coins to make x - coin) + 1
                    dp[x] = Math.min(dp[x], dp[x - coin] + 1);
                }
            }
        }
        // If the amount is still impossible, return -1
        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }
}