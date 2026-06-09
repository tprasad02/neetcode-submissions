class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        // Find the largest pile to establish the search range
        int max = piles[0];
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > max) {
                max = piles[i];
            }
        }

        // Binary search on Koko's eating speed
        int l = 1, r = max;
        int best = max;

        while (l <= r) {
            int m = (l + r) / 2;
            // Compute the total hours needed at speed m
            int numHours = 0;
            for (int j = 0; j < piles.length; j++) {
                numHours += piles[j] / m;
                // Round up when there are bananas remaining
                if (piles[j] % m != 0) {
                    numHours++;
                }
            }
            // Speed is too slow, search larger speeds
            if (numHours > h) {
                l = m + 1;
            }
            // Speed works, record it and try a smaller speed
            else {
                if (m < best) {
                    best = m;
                }
                r = m - 1;
            }
        }
        return best;
    }
}