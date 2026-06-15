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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        
        // Continue until one of the lists is exhausted
        while (list1 != null && list2 != null) {
            // Append the smaller node to the merged list
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            // Move the tail to the newly added node
            tail = tail.next;
        }
        // Append whichever list still has remaining nodes
        while (list1 != null) {
            tail.next = list1;
            list1 = list1.next;
            tail = tail.next;
        }
        while (list2 != null) {
            tail.next = list2;
            list2 = list2.next;
            tail = tail.next;
        }
        // The merged list starts after the dummy node
        return dummy.next;
    }
}