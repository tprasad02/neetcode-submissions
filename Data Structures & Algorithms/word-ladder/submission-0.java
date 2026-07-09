public class Solution {
    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {
        // Time: O(m^2 * n)
        // Space: O(m^2 * n)

        // If the end word isn't even in the dictionary,
        // it's impossible to reach it
        if (!wordList.contains(endWord) ||
            beginWord.equals(endWord)) {
            return 0;
        }

        Set<String> words = new HashSet<>(wordList);
        int res = 0;

        // Standard BFS queue
        Queue<String> q = new LinkedList<>();

        // Start our search from beginWord
        q.offer(beginWord);

        // Continue until there are no more words to explore
        while (!q.isEmpty()) {
            // We've moved one level deeper in the BFS
            // Every word currently in the queue is this
            // many transformations away from beginWord
            res++;

            // Process exactly one level of the BFS
            for (int i = q.size(); i > 0; i--) {
                String node = q.poll();

                // If we've reached the target,
                // return the number of transformations
                if (node.equals(endWord)) {
                    return res;
                }

                // Try changing every character position
                for (int j = 0; j < node.length(); j++) {

                    // Try replacing that character with
                    // every lowercase letter
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Skip the original character
                        // since it wouldn't create a new word
                        if (c == node.charAt(j)) {
                            continue;
                        }
                        // Build the neighboring word that differs
                        // by exactly one character
                        String nei =
                            node.substring(0, j)
                            + c
                            + node.substring(j + 1);

                        // If this new word exists in our dictionary,
                        // we've found a valid transformation
                        if (words.contains(nei)) {

                            // Add it to the next BFS level
                            q.offer(nei);

                            // Remove it immediately so we don't
                            // visit it again and create duplicates
                            words.remove(nei);
                        }
                    }
                }
            }
        }
        // We explored everything and never reached endWord
        return 0;
    }
}