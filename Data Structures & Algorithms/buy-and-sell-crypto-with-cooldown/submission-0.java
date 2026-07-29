public class Solution {
    public int maxProfit(int[] prices) {
        // O(n) time, O(time)
        
        int n = prices.length;

        // DP state for the next day
        int dp1_buy = 0, dp1_sell = 0;

        // DP state for buying two days ahead (after cooldown)
        int dp2_buy = 0;

        // Process days from last to first
        for (int i = n - 1; i >= 0; i--) {
            // Buy today or skip today
            int dp_buy = Math.max(dp1_sell - prices[i], dp1_buy);

            // Sell today or skip today
            int dp_sell = Math.max(dp2_buy + prices[i], dp1_sell);

            // Shift DP states for the next iteration
            dp2_buy = dp1_buy;
            dp1_buy = dp_buy;
            dp1_sell = dp_sell;
        }

        // Maximum profit starting from day 0 with permission to buy
        return dp1_buy;
    }
}