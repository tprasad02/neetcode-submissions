class Solution {
    public int search(int[] nums, int target) {
        // O(log(n)) time, O(1) space
        int l = 0;
        int r = nums.length - 1;

        // Binary search for the rotation point (minimum element)
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
        // Left and right converge to minimum element
        int pivot = l;

        // Decide which sorted half to search
        if (target >= nums[pivot] && target <= nums[nums.length - 1]) {
            l = pivot;
            r = nums.length - 1;
        } else {
            l = 0;
            r = pivot - 1;
        }

        // Standard binary search
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
