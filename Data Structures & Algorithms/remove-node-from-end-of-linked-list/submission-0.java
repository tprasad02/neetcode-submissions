/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node simplifies removing the head node
        ListNode dummy = new ListNode(0, head);
        // Left will eventually point to the node before the one to remove
        ListNode left = dummy;
        // Right is moved n nodes ahead of left
        ListNode right = head;

        // Create a gap of n nodes between left and right
        while (n > 0) {
            right = right.next;
            n--;
        }
        // Move both pointers together until right reaches the end
        // At that point, left is immediately before the node to remove
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        // Skip the nth node from the end (node to delete)
        left.next = left.next.next;

        // Return new head
        // This handles the case where the original head was removed
        return dummy.next;
    }
}