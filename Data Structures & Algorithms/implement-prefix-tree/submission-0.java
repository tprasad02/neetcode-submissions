public class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean endOfWord = false;
}

public class PrefixTree {
    // Root node of the trie (does not store a character)
    private TrieNode root;
    // Initialize an empty trie
    public PrefixTree() {
        root = new TrieNode();
    }

    // Time: O(n), Space: O(n) (new nodes created)
    // Inserts a word into the trie
    public void insert(String word) {
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

    // Time: O(n), Space: O(1)
    // Returns true if the exact word exists in the trie
    public boolean search(String word) {
        TrieNode cur = root;
        // Traverse the trie following each character
        for (char c : word.toCharArray()) {
            // If a character is missing, the word doesn't exist
            if (!cur.children.containsKey(c)) {
                return false;
            }
            // Move to the next node
            cur = cur.children.get(c);
        }
        // Word exists only if the last node marks the end of a word
        return cur.endOfWord;
    }

    // Time: O(n), Space: O(1)
    // Returns true if any word in the trie starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        // Traverse the trie following the prefix
        for (char c : prefix.toCharArray()) {
            // If a character is missing, no word has this prefix
            if (!cur.children.containsKey(c)) {
                return false;
            }
            // Move to the next node
            cur = cur.children.get(c);
        }
        // Successfully reached the end of the prefix
        return true;
    }
}