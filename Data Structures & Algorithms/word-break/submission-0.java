class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Time: O(n*m*t)
        // Where n is length of string s, m is num words in WordDict,
        // t is the max lenght of any word in WordDict
        // Space: O(n)

        // dp[i] means:
        // Can the prefix s[0...i-1] be segmented into dictionary words?
        boolean[] dp = new boolean[s.length() + 1];

        // Base case: the empty prefix can always be segmented
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {
            // No valid word segmentation ends at index i, so skip checking
            // dictionary words starting from here
            if (dp[i] == false) {
                continue;
            }
            // Try placing every dictionary word starting at i
            for (String w : wordDict) {
                // Make sure the word fits inside the string
                if (i + w.length() <= s.length() &&
                    s.substring(i, i + w.length()).equals(w)) {
                    // Since we've already segmented up to i,
                    // and w matches starting at i,
                    // we can now segment up to i + w.length()
                    dp[i + w.length()] = true;
                }
            }
        }
        return dp[s.length()];
    }
}