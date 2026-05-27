class Solution {
    public boolean isAnagram(String s, String t) {
        // // Solution 1: Sort each array alphabetically, and check if same
        // // Inefficient due to sorting, but good first solution
        // String[] sArr = s.split("");
        // String[] tArr = t.split("");
        // Arrays.sort(sArr);
        // Arrays.sort(tArr);
        // return Arrays.equals(sArr, tArr);


        // Solution 2: Use HashTable
        // Originally was using HashSet but this is for unique elements and does not work here
        // Use HashMap for counting frequency of each char in each string
        // O(n + m) time for n length of str1 and m length of str2, and O(1) space
        
        // Different lengths means they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        HashMap<Character, Integer> counts = new HashMap<>();

        // Count letters in s
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Remove letters using t
        for (char c : t.toCharArray()) {
            // Letter doesn't exist
            if (!counts.containsKey(c)) {
                return false;
            }
            counts.put(c, counts.get(c) - 1);
            
            // Remove key once frequency reaches 0
            // This lets us simply check counts.isEmpty() at the end
            if (counts.get(c) == 0) {
                counts.remove(c);
            }
        }

        // If all frequencies canceled out, map should now be empty
        return counts.isEmpty();
    }
}
