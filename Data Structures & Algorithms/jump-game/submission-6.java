class Solution {
    public boolean canJump(int[] nums) {
        // O(n) time, O(1) space

        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            // If at an index beyond max reachable pos, we are stuck
            if (i > farthest) {
                return false;
            }
            // Update the furthest reachable position
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }
}