class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Solution 1: Brute-force comparison
        // Idea: For each string, compare it against all other strings and group those 
        // that are anagrams using a character-frequency check.
        // Inefficient because every pair of strings may be compared, 
        // leading to repeated work across groups
        // O(n^2 * k) time, where n = num strings and k = string length



        // Solution 2 (more optimized): Sort each string internally and then use HashMap
        // Idea: Convert each string into a canonical form 
        // by sorting its characters so all anagrams become identical keys
        // Then group strings in a HashMap based on this sorted key
        // Frequency for a key is the number of items in a set (num of anagrams)
        // Sorting each string costs O(k log k)
        // This gives O(n * k log k) time
        
        // Key = sorted string
        // Value = list of original words
        // Map<String, List<String>> counts = new HashMap<>();

        // for (int i = 0; i < strs.length; i++) {
        //     char[] charArray = strs[i].toCharArray();
        //     Arrays.sort(charArray);
        //     String currSorted = new String(charArray);

        //     counts.putIfAbsent(currSorted, new ArrayList<String>());
        //     counts.get(currSorted).add(strs[i]);
        // }

        // List<List<String>> grammies = new ArrayList<>();

        // for (Map.Entry<String, List<String>> entry : counts.entrySet()) {
        //     grammies.add(entry.getValue());
        // }
        // return grammies;



        // Solution 3 (best): HashMap grouping via character frequencies
        // O(m*n) time, O(m) auxiliary space, where m is num strings
        // total space is O(m*n), n is length of longest string
        Map<String, List<String>> res = new HashMap<>();

        for (String s : strs) {
        // Step 1: Build a frequency count of characters (a-z only)
        // count[i] = how many times letter appears in the string
        // subtract 'a' in order to map a to 0, b to 1, c to 2, etc.
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            // Step 2: Convert frequency array into a "signature key"
            // All anagrams will produce the exact same frequency pattern
            String key = Arrays.toString(count);

            // Step 3: Group strings by this signature
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        // Step 4: Return all grouped anagram lists
        return new ArrayList<>(res.values());
    }
}