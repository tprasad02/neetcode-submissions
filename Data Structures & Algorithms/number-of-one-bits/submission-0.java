class Solution {
    public int hammingWeight(int n) {
        // O(1) time, O(1) space
        int res = 0;

        // Remove the lowest set bit each iteration
        while (n != 0) {
            n &= n - 1;
            res++;
        }

        return res;
    }
}