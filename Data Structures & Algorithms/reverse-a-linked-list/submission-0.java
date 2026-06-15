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

public class Solution {
    public ListNode reverseList(ListNode head) {
        // 'prev' will become the new head of the reversed list
        ListNode prev = null;
        // Start traversing from the original head
        ListNode curr = head;

        while (curr != null) {
            // Save the next node before changing any pointers
            ListNode temp = curr.next;
            // Reverse the current node's pointer
            curr.next = prev;
            // Move both pointers one step forward
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}