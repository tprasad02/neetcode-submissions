class Solution {
    public int countSubstrings(String s) {
        // O(n^2) time, O(1) space
        
        int len = 0;
        int idx = 0;
        int count = 0;
        // Try every possible center
        for (int i = 0; i < s.length(); i++) {
            // Odd palindrome - one middle letter: "aba"
            int l = i, r = i;
            while (l >= 0 && r < s.length() &&
                s.charAt(l) == s.charAt(r)) {
                // Found a longer palindrome
                if (r - l + 1 > len) {
                    idx = l;
                    len = r - l + 1;
                }
                // Grow outward from the center
                l--;
                r++;
                count++;
            }
            // Even palindrome - two middle letters: "abba"
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() &&
                s.charAt(l) == s.charAt(r)) {
                // Found a longer palindrome
                if (r - l + 1 > len) {
                    idx = l;
                    len = r - l + 1;
                }
                // Grow outward from the center
                l--;
                r++;
                count++;
            }
        }
        return count;
    }
}