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
    public boolean hasCycle(ListNode head) {
        // Stores every node we've already visited
        // The HashSet stores ListNode objects (references), not their values
        // Therefore, two different nodes with the same value are treated as different
        Set<ListNode> visited = new HashSet<>();
        ListNode tail = head;
        while (tail != null) {
            // If we've already visited this exact node, we've looped
            // back to it, so the list contains a cycle
            if (visited.contains(tail)) {
                return true;
            }
            // Mark the current node as visited
            visited.add(tail);
            tail = tail.next;
        }
        return false;
    }
}
