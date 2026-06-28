class Solution {
    public List<List<String>> partition(String s) {
        // Stores all valid palindrome partitions
        List<List<String>> res = new ArrayList<>();
        // Tracks the current partition we're building
        List<String> partition = new ArrayList<>();
        // Start partitioning from the beginning of the string
        dfs(s, 0, partition, res);
        return res;
    }

    private void dfs(String s, int start,
                     List<String> partition,
                     List<List<String>> res) {
        // Invariant:
        // We've already partitioned s[0...start-1] into palindromes
        // This call tries every possible place to end the next palindrome

        // Base case: we've partitioned the entire string
        if (start == s.length()) {
            res.add(new ArrayList<>(partition));
            return;
        }

        // Try every possible ending index for the next substring
        for (int end = start; end < s.length(); end++) {
            // Only recurse if the current substring is a palindrome
            if (isPalindrome(s, start, end)) {
                // Choose this palindrome
                partition.add(s.substring(start, end + 1));
                // Partition the remaining suffix
                dfs(s, end + 1, partition, res);
                // Backtrack and try a different ending position
                partition.remove(partition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        // Check whether s[left...right] is a palindrome
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}