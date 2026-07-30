public class Solution {
    public int singleNumber(int[] nums) {
        // O(n) time, O(1) space
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}