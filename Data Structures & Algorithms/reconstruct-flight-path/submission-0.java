class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // Time: O(ElogE)
        // Space: O(E)
        
        // Hierholzer's Algorithm:
        // We must use every ticket exactly once, start at JFK,
        // and return the lexicographically smallest valid itinerary
        // Simulate DFS using a stack:
        // - Keep moving forward while tickets exist
        // - When stuck, backtrack and record the airport
        
        // Build adjacency list with a min-heap
        Map<String, PriorityQueue<String>> adj = new HashMap<>();

        for (List<String> ticket : tickets) {
            adj.computeIfAbsent(ticket.get(0),
                k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        LinkedList<String> res = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            String curr = stack.peek();
            // If no outgoing flights remain, we are stuck
            // Backtrack and add the airport to the front of the result
            if (!adj.containsKey(curr) || adj.get(curr).isEmpty()) {
                res.addFirst(stack.pop());
            } else {
                // Keep moving forward using the smallest destination
                stack.push(adj.get(curr).poll());
            }
        }
        return res;
    }
}