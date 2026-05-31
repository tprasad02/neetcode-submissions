public class Solution {
    public int trap(int[] height) {
        // Two pointers: O(n) time, O(1) space
        if (height == null || height.length == 0) {
            return 0;
        }
        int l = 0, r = height.length - 1;
        
        // Tallest wall seen so far from each side
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            // The smaller side determines amount of water
            if (leftMax < rightMax) {
                l++;
                // Update left boundary
                leftMax = Math.max(leftMax, height[l]);
                // Water above current bar
                res += leftMax - height[l];
            } else {
                r--;
                // Update right boundary
                rightMax = Math.max(rightMax, height[r]);
                // Water above current bar
                res += rightMax - height[r];
            }
        }
        return res;
    }
}