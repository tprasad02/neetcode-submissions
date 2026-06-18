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

// Solution 1: Merge lists one by one
// O(n * k) time, O(1) space
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // Repeatedly merge the next list with the merged result so far
        // After each iteration, lists[i] stores the merged list from 0...i
        for (int i = 1; i < lists.length; i++) {
            lists[i] = merge(lists[i], lists[i - 1]);
        }
        // The last element contains all lists merged together
        return lists[lists.length - 1];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Continue until one of the lists is exhausted
        while (l1 != null && l2 != null) {
            // Always attach the smaller node to keep the list sorted
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            // Advance to the end of the merged list
            curr = curr.next;
        }

        // Only one list may have remaining nodes
        // Since it is already sorted, append it directly
        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        return dummy.next;
    }
}

// // Solution 2: Divide and conquer (recursion)
// // O(n log k) time, O(log k) space
// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length == 0) {
//             return null;
//         }
//         // Recursively split the array of lists until each subproblem
//         // contains a single list, then merge the results back together
//         return divide(lists, 0, lists.length - 1);
//     }

//     private ListNode divide(ListNode[] lists, int l, int r) {
//         if (l > r) {
//             return null;
//         }
//         // Base case: one list is already "merged"
//         if (l == r) {
//             return lists[l];
//         }
//         // Divide the range into two halves
//         int mid = l + (r - l) / 2;
//         // Recursively merge each half
//         ListNode left = divide(lists, l, mid);
//         ListNode right = divide(lists, mid + 1, r);
//         // Merge the two sorted halves
//         return conquer(left, right);
//     }

//     private ListNode conquer(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(0);
//         ListNode curr = dummy;
//         // Merge while both lists still contain nodes
//         while (l1 != null && l2 != null) {
//             // Attach the smaller node to keep the merged list sorted
//             if (l1.val <= l2.val) {
//                 curr.next = l1;
//                 l1 = l1.next;
//             } else {
//                 curr.next = l2;
//                 l2 = l2.next;
//             }
//             // Advance to the end of the merged list
//             curr = curr.next;
//         }
//         // Append whichever list still has remaining nodes
//         if (l1 != null) {
//             curr.next = l1;
//         } else {
//             curr.next = l2;
//         }
//         return dummy.next;
//     }
// }
