class Solution {
    public int jump(int[] nums) {
        // O(n) time, O(1) space

        // res = number of jumps, [l, r] = current reachable range
        int res = 0, l = 0, r = 0;

        while (r < nums.length - 1) {
            // Find the farthest index reachable from current range
            int farthest = 0;
            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            // Move to next range and count jumps
            l = r + 1;
            r = farthest;
            res++;
        }

        return res;
    }
}