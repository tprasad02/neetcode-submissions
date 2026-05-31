class Solution {
    public int maxProfit(int[] prices) {
        // Brute force
        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            int maxFuture = 0;
            for (int j = i+1; j < prices.length; j++){
                if (prices[j] > maxFuture){
                    maxFuture = prices[j];
                    System.out.println(maxFuture);
                }
            }
            System.out.println(maxFuture);
            if ((maxFuture-prices[i]) > profit){
                profit = (maxFuture-prices[i]);
            }
        }    
        return profit;
    }
}
