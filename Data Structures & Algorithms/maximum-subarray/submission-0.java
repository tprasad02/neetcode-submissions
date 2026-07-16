public class Solution {
    public int maxSubArray(int[] nums) {
        // Time: O(n)
        // Space: O(1)

        int maxSub = nums[0];
        int curSum = 0;

        for (int num : nums) {
            // If the current subarray sum is negative,
            // it can only hurt any future subarray we extend
            // Start a new subarray from the current number instead
            if (curSum < 0) {
                curSum = 0;
            }
            curSum += num;
            maxSub = Math.max(maxSub, curSum);
        }

        return maxSub;
    }
}