class Solution {
    public List<Integer> partitionLabels(String s) {
        // Time: O(n)
        // Space: O(1)

        List<Integer> result = new ArrayList<>();
        
        // Count remaining occurrences of each character
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        // Track characters that belong to the current partition
        int[] current = new int[26];
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            
            char c = s.charAt(i);
            int idx = c - 'a';
            
            // Current character is now used once
            freq[idx]--;
            current[idx] = 1;
            
            count++;
            
            boolean canSplit = true;
            
            // If any character in the current partition appears later,
            // we must extend this partition
            for (int j = 0; j < 26; j++) {
                if (current[j] == 1 && freq[j] > 0) {
                    canSplit = false;
                    break;
                }
            }
            
            // All characters in this partition are complete
            if (canSplit) {
                result.add(count);
                count = 0;
                current = new int[26];
            }
        }
        
        return result;
    }
}