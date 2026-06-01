public class Solution {
    public int characterReplacement(String s, int k) {
        // Sliding window, O(n) time, O(m) space
        
        // frequency map of characters in current window
        HashMap<Character, Integer> count = new HashMap<>();
        int res = 0;
        // l = left pointer of sliding window
        // maxf = count of most frequent char in current window
        int l = 0, maxf = 0;

        for (int r = 0; r < s.length(); r++) {
            // expand window: include s[r]
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            // track most frequent character in window
            maxf = Math.max(maxf, count.get(s.charAt(r)));
            // window is invalid if chars to change > k
            // i.e. window size - most frequent char count > k
            while ((r - l + 1) - maxf > k) {
                // shrink window from left
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                l++;
                // do NOT track "exceptions" or indices
                // validity is derived purely from frequency, not per-character logic
            }
            // update best valid window
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}