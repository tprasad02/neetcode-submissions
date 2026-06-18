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
    public ListNode reverseKGroup(ListNode head, int k) {
        // O(n) time, O(1) space

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // Points to the node before the current group
        ListNode groupPrev = dummy;

        while (groupPrev != null) {
            // Check whether there are at least k nodes remaining
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            // Not enough nodes to reverse
            if (kth == null) {
                return dummy.next;
            }

            ListNode groupNext = kth.next;

            // Reverse this group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect the reversed group back into the list
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }
        return dummy.next;
    }
}