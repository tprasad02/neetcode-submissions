/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // O(n) time, O(n) space
        if (head == null) {
            return null;
        }
        
        // Maps each original node to its copy
        // Can't connect the copied nodes' next and random pointers immediately
        // in the HashMap because the copied versions of those nodes may not exist yet
        // Original node contains all the pointer information
        // which we use once we have made copies of all nodes
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        // Create a copy of every node
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;

        // Connect the next and random pointers
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next = map.get(curr.next);
            copy.random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}