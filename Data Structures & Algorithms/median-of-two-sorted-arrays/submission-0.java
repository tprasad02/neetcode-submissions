public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // O(log(m+n)) time, O(1) space
        
        // Median is the middle of the combined sorted arrays
        int left = (nums1.length + nums2.length + 1) / 2;
        int right = (nums1.length + nums2.length + 2) / 2;

        // For odd length, left == right
        // For even length, average the two middle values
        return (getKth(nums1, nums1.length, nums2, nums2.length, left, 0, 0) +
                getKth(nums1, nums1.length, nums2, nums2.length, right, 0, 0)) / 2.0;
    }

    private int getKth(int[] a, int m, int[] b, int n, int k, int aStart, int bStart) {
        // Always make a the shorter remaining array
        if (m > n) {
            return getKth(b, n, a, m, k, bStart, aStart);
        }
        // If a is empty, kth value must be in b
        if (m == 0) {
            return b[bStart + k - 1];
        }
        // Smallest remaining value
        if (k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }
        // Compare k/2 elements from each array
        int i = Math.min(m, k / 2);
        int j = Math.min(n, k / 2);

        // Discard the smaller block because kth cannot be there
        if (a[aStart + i - 1] > b[bStart + j - 1]) {
            return getKth(a, m, b, n - j, k - j, aStart, bStart + j);
        } else {
            return getKth(a, m - i, b, n, k - i, aStart + i, bStart);
        }
    }
}