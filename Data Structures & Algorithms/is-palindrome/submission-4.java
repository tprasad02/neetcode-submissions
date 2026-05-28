class Solution {
    public boolean isPalindrome(String s) {
        // Solution: two pointers moving inwards
        // [^a-zA-Z0-9] matches everything that is NOT a letter or number
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(cleaned);
        int j = cleaned.length()-1;
        for (int i = 0; i < j; i++){
            String start = cleaned.substring(i, i+1);
            System.out.println(start);
            String end = cleaned.substring(j, j+1);
            if (start.equalsIgnoreCase(end)) j--;
            else return false;
        }
        return true;
    }
}
