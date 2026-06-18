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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // We have two pointers, one at each list
        // Since the digits are stored in reverse order, we can add
        // corresponding digits one by one, carrying over any extra
        // value to the next pair of digits
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            // Add current digit from first list
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            // Add current digit from second list
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // The current digit is the ones place of the sum
            curr.next = new ListNode(sum % 10);
            // Carry the tens place to the next addition
            carry = sum / 10;
            curr = curr.next;
        }
        return dummy.next;
    }
}
