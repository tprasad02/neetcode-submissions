public class Solution {
    public int longestConsecutive(int[] nums) {
        // Use HashSet to check existence
        // O(n) time, O(n) space
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longest = 0;
        // Iterate only from sequence starting points (num where num-1 doesn't exist)
        for (int num : numSet) {
            // If there's no previous number, this is the start of a consecutive chain
            if (!numSet.contains(num - 1)) {
                int length = 1;
                // Expand forward greedily to count full consecutive sequence
                while (numSet.contains(num + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}