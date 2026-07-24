public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Bellman-Ford algorithm

        // Store cheapest price to reach each city
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        // Relax edges k + 1 times
        // This way, we don't need to check if k is exceeded
        for (int i = 0; i <= k; i++) {

            // Copy prices to avoid using more than one new flight per iteration
            int[] tmpPrices = Arrays.copyOf(prices, n);

            // Check every flight
            for (int[] flight : flights) {
                int s = flight[0];
                int d = flight[1];
                int p = flight[2];

                // Skip if source city is unreachable
                if (prices[s] == Integer.MAX_VALUE) {
                    continue;
                }

                // Update if this route is cheaper
                if (prices[s] + p < tmpPrices[d]) {
                    tmpPrices[d] = prices[s] + p;
                }
            }

            // Update prices for the next iteration
            prices = tmpPrices;
        }

        // Return -1 if destination is unreachable
        if (prices[dst] == Integer.MAX_VALUE) {
            return -1;
        }
        return prices[dst];
    }
}