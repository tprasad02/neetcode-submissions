public class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        // Binary search for the rotation point
        while (l < r) {
            int m = l + (r - l) / 2;
            // Minimum is in the left half
            if (nums[m] < nums[r]) {
                r = m;
            }
            // Minimum is in the right half
            else {
                l = m + 1;
            }
        }
        // Left and right converge to the minimum element
        return nums[l];
    }
}