class TrieNode {
    // Child node for each lowercase letter ('a' to 'z')
    TrieNode[] children = new TrieNode[26];
    // True if this node marks the end of a complete word
    boolean word = false;
}

class WordDictionary {
    // Root node of the trie
    private TrieNode root;

    // Initialize an empty trie
    public WordDictionary() {
        root = new TrieNode();
    }

    // Time: O(n)
    // Space: O(n) in the worst case (new nodes created)
    // Inserts a word into the trie
    public void addWord(String word) {
        TrieNode node = root;
        // Traverse each character in the word
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // Create a node if this path doesn't exist
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            // Move to the next node
            node = node.children[index];
        }
        // Mark the end of the word
        node.word = true;
    }

    // Time: O(n)
    // Space: O(n) recursion depth
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // Searches the trie starting from index i
    private boolean dfs(String word, int i, TrieNode node) {
        // Traverse the remaining characters
        for (; i < word.length(); i++) {
            char c = word.charAt(i);
            // '.' can match any letter, so try every child
            // This is backtracking
            if (c == '.') {
                for (TrieNode child : node.children) {
                    // If any path matches, the word exists
                    if (child != null && dfs(word, i + 1, child)) {
                        return true;
                    }
                }
                // No child matched
                return false;
            }
            int index = c - 'a';
            // Character doesn't exist in the trie
            if (node.children[index] == null) {
                return false;
            }
            // Continue down the matching path
            node = node.children[index];
        }
        // We've reached the end of the search word
        return node.word;
    }
}