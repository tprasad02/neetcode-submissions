class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Sliding window problem
        // Use HashSet to maintain list of unique characters
        int l = 0;
        int r = 0;
        int currLen = 0;
        int maxLen = 0;
        Set<String> seen = new HashSet<>();
        for (int idx = 0; r < s.length(); idx++){
            String curr = s.substring(r, r+1);
            if (!(seen.contains(curr))){ // expand window
                seen.add(curr);
                currLen++;
                maxLen = Math.max(currLen, maxLen);
                r++;
            }
            else { // start new string, shift window
                seen.clear();
                currLen = 0;
                l++;
                r = l;
            }
        }
        return maxLen;
    }
}
