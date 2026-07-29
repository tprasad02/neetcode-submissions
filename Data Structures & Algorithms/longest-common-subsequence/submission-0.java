class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // O(m*n) time, O(m*n) space

        int m = text1.length();
        int n = text2.length();

        // dp[i][j] = LCS length of text1[i:] and text2[j:]
        int[][] dp = new int[m + 1][n + 1];

        // Build the table from the end of both strings
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Matching characters can be part of the subsequence
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } 
                // Skip one character and take the better option
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // LCS of the two full strings
        return dp[0][0];
    }
}