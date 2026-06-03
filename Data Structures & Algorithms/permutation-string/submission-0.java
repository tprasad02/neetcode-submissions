public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Impossible if s1 is longer than s2
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Build counts for s1 and the first window of s2
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // Number of letters whose frequencies currently match
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) {
                matches++;
            }
        }

        int l = 0;

        // Slide the window across s2
        for (int r = s1.length(); r < s2.length(); r++) {

            // All 26 frequencies match -> permutation found
            if (matches == 26) {
                return true;
            }

            // Add new right character
            int index = s2.charAt(r) - 'a';
            s2Count[index]++;

            // Update matches for this character
            if (s1Count[index] == s2Count[index]) {
                matches++;
            } else if (s1Count[index] + 1 == s2Count[index]) {
                matches--;
            }

            // Remove left character
            index = s2.charAt(l) - 'a';
            s2Count[index]--;

            // Update matches for this character
            if (s1Count[index] == s2Count[index]) {
                matches++;
            } else if (s1Count[index] - 1 == s2Count[index]) {
                matches--;
            }

            l++;
        }

        // Check the final window
        return matches == 26;
    }
}