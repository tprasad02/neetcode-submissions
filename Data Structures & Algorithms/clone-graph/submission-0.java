/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

public class Solution {
    // Time: O(V + E)
    // Space: O(V)
    // Creates a deep copy of the graph
    public Node cloneGraph(Node node) {
        // Maps each original node to its cloned node
        Map<Node, Node> oldToNew = new HashMap<>();
        return dfs(node, oldToNew);
    }

    // Recursively clones the graph using DFS
    private Node dfs(Node node, Map<Node, Node> oldToNew) {
        // Empty graph
        if (node == null) {
            return null;
        }
        // If we've already cloned this node,
        // return the existing copy to avoid cycles
        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }
        // Create a copy of the current node
        Node copy = new Node(node.val);
        // Store it before exploring neighbors
        // so cycles don't cause infinite recursion
        oldToNew.put(node, copy);
        // Clone each neighbor and connect it to the copy
        for (Node nei : node.neighbors) {
            copy.neighbors.add(dfs(nei, oldToNew));
        }
        return copy;
    }
}