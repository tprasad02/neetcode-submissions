class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > max){
                max = piles[i];
            }
        }

        int l = 1, r = max;
        int best = max;

        while (l <= r) {
            int m = (l + r) / 2;
            int numHours = 0;
            for (int j = 0; j < piles.length; j++){
                numHours += piles[j]/m;
                if (piles[j]%m != 0){
                    numHours++;
                }
            }
            System.out.println(m);
            System.out.println(numHours);
            if (numHours > h) {
                l = m + 1;
            } else if (numHours <= h) {
                if (m < best){
                    best = m;
                }
                r = m - 1;
            } else {
                return best;
            }
        }
        return best;
    }
}
