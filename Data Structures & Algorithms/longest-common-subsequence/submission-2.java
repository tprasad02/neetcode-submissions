class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // O(m*n) time, O(m*n) space

        // Use the shorter string for the DP array to save space
        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // dp[j] stores the LCS length for the current row
        int[] dp = new int[text2.length() + 1];

        // Process text1 from right to left
        for (int i = text1.length() - 1; i >= 0; i--) {
            // Stores the previous diagonal value
            int prev = 0;

            // Process text2 from right to left
            for (int j = text2.length() - 1; j >= 0; j--) {
                // Save current dp[j] before updating it
                int temp = dp[j];

                // Matching characters extend the LCS by 1
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[j] = 1 + prev;
                } else {
                    // Skip one character and take the better result
                    dp[j] = Math.max(dp[j], dp[j + 1]);
                }

                // Current value becomes the diagonal for the next cell
                prev = temp;
            }
        }

        // LCS of the two full strings
        return dp[0];
    }
}