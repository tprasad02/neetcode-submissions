class Solution {
    public String minWindow(String s, String t) {
        if (t.isEmpty()) return "";

        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        int required = target.size(); // distinct chars we must satisfy
        int have = 0;

        int l = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            // Expand window
            window.put(c, window.getOrDefault(c, 0) + 1);
            // Did this character just become satisfied?
            if (target.containsKey(c) && window.get(c).intValue() == target.get(c).intValue()) { 
                have++; }
            // Window is valid
            while (have == required) {
                // Update best answer
                int currLen = r - l + 1;
                if (currLen < bestLen) {
                    bestLen = currLen;
                    bestStart = l;
                }
                // Shrink from the left
                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                // Did removing this character make it invalid?
                if (target.containsKey(leftChar)
                        && window.get(leftChar) < target.get(leftChar)) {
                    have--;
                }
                l++;
            }
        }
        if (bestLen == Integer.MAX_VALUE) {
            return "";
            }
        return s.substring(bestStart, bestStart + bestLen);
    }
}