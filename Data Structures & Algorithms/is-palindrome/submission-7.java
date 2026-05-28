class Solution {
    public boolean isPalindrome(String s) {
        // Solution: two pointers moving inwards, stop when they cross
        // O(n) time, O(n) space
        // Could be reduced to O(1) space if we skipped over non alpha-numeric chars 
        // using a isAlphaNumeric helper function, rather than creating a new stripped str 

        // [^a-zA-Z0-9] matches everything that is NOT a letter or number
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "");
        int j = cleaned.length()-1;
        for (int i = 0; i < j; i++){
            String start = cleaned.substring(i, i+1);
            String end = cleaned.substring(j, j+1);
            if (start.equalsIgnoreCase(end)) j--;
            else return false;
        }
        return true;
    }
}
