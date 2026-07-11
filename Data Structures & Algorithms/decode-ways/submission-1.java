class Solution {
    public int numDecodings(String s) {
        // O(n) time, O(n) space
        int n = s.length();

        // dp[i] = number of ways to decode the first i characters
        // We use DP because the same prefixes get recomputed
        // many times in the decision tree
        int[] dp = new int[n + 1];

        // Empty string has one valid decoding
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // Choice 1: decode one digit
            // The current digit cannot be '0'
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // Choice 2: decode two digits
            // The number formed by the previous two characters
            // must be between 10 and 26
            if (i >= 2 &&
                (s.charAt(i - 2) == '1' ||
                (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        // Number of ways to decode the entire string
        return dp[n];
    }
}