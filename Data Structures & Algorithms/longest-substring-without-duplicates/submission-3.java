class Solution {
    public int lengthOfLongestSubstring(String s) {
        // // Solution 1: Brute force (O(n^2) time, O(n) space)
        // // Use HashSet to maintain list of unique characters
        // int l = 0;
        // int r = 0;
        // int currLen = 0;
        // int maxLen = 0;
        // Set<String> seen = new HashSet<>();
        // for (int idx = 0; r < s.length(); idx++){
        //     String curr = s.substring(r, r+1);
        //     if (!(seen.contains(curr))){ // expand window
        //         seen.add(curr);
        //         currLen++;
        //         maxLen = Math.max(currLen, maxLen);
        //         r++;
        //     }
        //     else {
        //         seen.clear();
        //         currLen = 0;
        //         l++;
        //         r = l;
        //     }
        // }
        // return maxLen;


        // Solution 2: Sliding window
        
        // l = left boundary of the current valid window
        int l = 0;
        // maxLen tracks the best window seen so far
        int maxLen = 0;

        // Map stores: character -> LAST index where it was seen
        // Note: this is NOT "what's in the window", just memory of positions
        Map<Character, Integer> seen = new HashMap<>();

        for (int r = 0; r < s.length(); r++) {
            char curr = s.charAt(r);
            
            // If we've seen this character before
            if (seen.containsKey(curr)) {
                // Only matters if that previous index is still inside window
                // Move left pointer forward if needed
                // We take max because:
                //  - l should never move backward
                //  - old duplicates outside window must be ignored
                l = Math.max(l, seen.get(curr) + 1);
            }

            // Update last seen index of current character
            seen.put(curr, r);

            // Current window is [l, r]
            // Length = r - l + 1
            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}
