class Solution {
    public int maxProfit(int[] prices) {
        // // Brute force: O(n^2)
        // int profit = 0;
        // for (int i = 0; i < prices.length; i++){
        //     int maxFuture = 0;
        //     for (int j = i+1; j < prices.length; j++){
        //         if (prices[j] > maxFuture){
        //             maxFuture = prices[j];
        //         }
        //     }
        //     if ((maxFuture-prices[i]) > profit){
        //         profit = (maxFuture-prices[i]);
        //     }
        // }    
        // return profit;

        // Solution 2: two pointers (O(n) time, O(1) space)
        int l = 0, r = 1;
        int maxP = 0;

        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                int profit = prices[r] - prices[l];
                maxP = Math.max(maxP, profit);
            } else {
                l = r;
            }
            r++;
        }
        return maxP;
    }
}
