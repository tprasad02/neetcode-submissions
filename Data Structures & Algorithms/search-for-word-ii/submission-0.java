class TrieNode {
    // Child node for each lowercase letter
    TrieNode[] children = new TrieNode[26];
    // Stores the complete word if this node marks its end
    String word = null;
}

class Solution {
    private List<String> result = new ArrayList<>();
    public List<String> findWords(char[][] board, String[] words) {
        // Build a trie containing every word
        TrieNode root = buildTrie(words);
        // Start a DFS from every cell on the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                dfs(board, row, col, root);
            }
        }
        return result;
    }

    // Inserts every word into the trie
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            // Store the entire word so we can add it directly later
            node.word = word;
        }
        return root;
    }

    // Backtracking search from a board cell
    private void dfs(char[][] board, int row, int col, TrieNode node) {
        // Out of bounds
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length) {
            return;
        }
        char c = board[row][col];
        // Already visited
        if (c == '#') {
            return;
        }
        // Current letter isn't a valid trie path
        node = node.children[c - 'a'];
        if (node == null) {
            return;
        }
        // Found a complete word
        if (node.word != null) {
            result.add(node.word);
            // Prevent duplicate additions
            node.word = null;
        }
        // Mark this cell as visited
        board[row][col] = '#';
        // Explore all four directions
        dfs(board, row + 1, col, node);
        dfs(board, row - 1, col, node);
        dfs(board, row, col + 1, node);
        dfs(board, row, col - 1, node);
        // Restore the cell (backtrack)
        board[row][col] = c;
    }
}