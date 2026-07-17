class Solution {
    public boolean canJump(int[] nums) {
        // O(n) time, O(1) space

        // Start with the last index as our current goal
        // We want to know if we can eventually reach this position
        int goal = nums.length - 1;

        // Work backwards
        for (int i = nums.length - 2; i >= 0; i--) {
            // If we can jump from index i to the current goal
            // (or beyond it), then index i becomes the new goal
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}