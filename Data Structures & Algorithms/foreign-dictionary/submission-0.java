class Solution {
    public String foreignDictionary(String[] words) {
        // Time: O(N+V+E)
        // Space: O(V+E)
        // where V is num of unique chars, E is num of edges
        // N is sum of length of all strings

        // Adjacency list: each character points to characters that
        // must come after it in the alien alphabet
        Map<Character, Set<Character>> adj = new HashMap<>();
        // Indegree: number of characters that must come before each character
        Map<Character, Integer> indegree = new HashMap<>();

        // Add every character to the graph, even if it has no edges
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Compare every pair of adjacent words
        // The first different character tells us the ordering between
        // those two characters
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());

            // Invalid case:
            // If w1 is longer than w2 but starts with all of w2,
            // then w1 cannot come before w2 in lexicographical order
            // Example: ["abc", "ab"] -> invalid
            if (w1.length() > w2.length() &&
                w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
                return "";
            }

            // Find the first position where the two words differ
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    // We know c1 must come before c2
                    // Only add the edge if it doesn't already exist,
                    // otherwise we would incorrectly increase indegree
                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    // Once we find the first difference, later characters
                    // do not affect the lexicographical ordering
                    break;
                }
            }
        }

        // Start with all characters that have no prerequisites
        // These characters can appear first in the ordering
        Queue<Character> q = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                q.offer(c);
            }
        }

        StringBuilder res = new StringBuilder();

        // Kahn's Algorithm for Topological Sort
        while (!q.isEmpty()) {
            // Take a character whose prerequisites have all been processed
            char char_ = q.poll();
            res.append(char_);
            // Remove this character's outgoing edges
            for (char neighbor : adj.get(char_)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                // If all prerequisites for the neighbor are now satisfied,
                // it can be added to the queue
                if (indegree.get(neighbor) == 0) {
                    q.offer(neighbor);
                }
            }
        }
        // If we couldn't process every character, there must be a cycle
        // in the graph, so no valid alphabet ordering exists
        if (res.length() != indegree.size()) {
            return "";
        }
        // Return the topological ordering of the characters
        return res.toString();
    }
}