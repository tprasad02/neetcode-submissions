public class TrieNode {
    // Maps each character to its corresponding child node
    HashMap<Character, TrieNode> children = new HashMap<>();
    // True if this node marks the end of a complete word
    boolean endOfWord = false;
}

class WordDictionary {
    // Root node of the trie (does not store a character)
    private TrieNode root;
    
    // Initialize an empty trie
    public WordDictionary() {
        root = new TrieNode();
    }

    // Time: O(n), Space: O(n) in the worst case
    public void addWord(String word) {
        TrieNode cur = root;
        // Traverse each character in the word
        for (char c : word.toCharArray()) {
            // Create a new node if this character doesn't already exist
            cur.children.putIfAbsent(c, new TrieNode());
            // Move to the child node
            cur = cur.children.get(c);
        }
        // Mark the final node as the end of a valid word
        cur.endOfWord = true;
    }

    // Time: O(26^n) worst case when every character is '.'
    // Space: O(n) recursion depth
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // Recursively searches the trie starting from index i
    private boolean dfs(String word, int i, TrieNode node) {
        // If we've processed every character,
        // the word exists only if we're at the end of a stored word
        if (i == word.length()) {
            return node.endOfWord;
        }
        char c = word.charAt(i);
        // '.' can match any character, so try every child
        // Use backtracking
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                // If any path matches, the word exists
                if (dfs(word, i + 1, child)) {
                    return true;
                }
            }
            // None of the possible paths matched
            return false;
        }
        // If the current character doesn't exist, the word isn't present
        if (!node.children.containsKey(c)) {
            return false;
        }
        // Continue searching from the matching child
        return dfs(word, i + 1, node.children.get(c));
    }
}