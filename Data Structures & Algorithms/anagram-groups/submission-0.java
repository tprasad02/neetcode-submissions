class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Solution 1: Brute-force comparison
        // Idea: For each string, compare it against all other strings and group those 
        // that are anagrams using a character-frequency check.
        // Inefficient because every pair of strings may be compared, 
        // leading to repeated work across groups
        // O(n^2 * k) time, where n = num strings and k = string length

        // Solution 2 (optimized): Sort each string internally and then use HashMap
        // Idea: Convert each string into a canonical form 
        // by sorting its characters so all anagrams become identical keys
        // Then group strings in a HashMap based on this sorted key
        // Frequency for a key is the number of items in a set (num of anagrams)
        // Sorting each string costs O(k log k)
        // This gives O(n * k log k) time
        
        // Key = sorted string
        // Value = list of original words
        Map<String, List<String>> counts = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String currSorted = new String(charArray);

            counts.putIfAbsent(currSorted, new ArrayList<String>());
            counts.get(currSorted).add(strs[i]);
        }

        List<List<String>> grammies = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : counts.entrySet()) {
            grammies.add(entry.getValue());
        }
        return grammies;        
    }
}