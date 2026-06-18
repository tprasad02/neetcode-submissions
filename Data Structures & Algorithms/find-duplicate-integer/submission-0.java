class Solution {
    public int findDuplicate(int[] nums) {
        // Think of the array as a linked list where:
        // index -> nums[index]
        // Since there are n + 1 indices but only n possible values,
        // there must be a cycle
        // The duplicate number is the entrance to that cycle

        // Find the intersection point inside the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Move one pointer back to the start
        // Advance both one step at a time until they meet again
        // The meeting point is the duplicate number
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}